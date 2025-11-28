package marcel.demonworld.armygenerator.encoding;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SecondSubFactionDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.EquipmentTypes;
import marcel.demonworld.armygenerator.entities.ItemCard;
import marcel.demonworld.armygenerator.entities.UnitCard;
import marcel.demonworld.armygenerator.exceptions.AppException;
import marcel.demonworld.armygenerator.mappingInterfaces.ItemCardMapper;
import marcel.demonworld.armygenerator.mappingInterfaces.SecondSubFactionMapper;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardMapper;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import marcel.demonworld.armygenerator.services.game.SecondSubFactionService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ArmyList objects have the problem, that the list property is a nested list of objects. I.e., it is a list containing
 * at least one UnitCardDTO object which in turn may contain a list of one or more ItemCardDTO object.
 * Such a nested structure is, to be uncouth, a pain in the butt to persist in SQL.
 * To avoid the hassle and added complexity of writing it into a JSON field,
 * the list is instead encoded as a simple string. Every UnitCardDTO and ItemDTO is uniquely represented by its ID.
 * Individual unitCard IDs are separated by a dot. If a UnitCard contains a list of one or more ItemCardDTOs,
 * then the ItemCard list is announced by a colon, is comma-separated and ends with a dot.
 * If a unitCard has a second sub faction, then the second sub faction is separated from the unit Card id
 * with a dash. (Currently in the game this applies only to the Thain faction, see the army book "Thain" for any questions)
 * <p>
 * Example: "11.12.23.5:9,23,4.12" -> army list with 5 units, the 4th unit has 3 items.
 */
@Component
@Primary
public class ArmyListEncoderImpl implements ArmyListEncoder {

    @Autowired
    UnitCardService unitCardService;

    @Autowired
    ItemCardService itemCardService;

    @Autowired
    SecondSubFactionService secondSubFactionService;

    @Autowired
    UnitCardMapper unitCardMapper;

    @Autowired
    ItemCardMapper itemCardMapper;

    @Autowired
    SecondSubFactionMapper secondSubFactionMapper;

    /**
     * Takes a list of UnitCard objects and the optionally contained ItemCard objects
     * and turns them into a String according to the rules (see class description)
     *
     * @param armyList a kut of UnitCard objects that can contains ItemCard objects
     * @return a normal Java String.
     */
    @Override
    public String encode(List<UnitCardDTO> armyList) {

        StringBuilder codedList = new StringBuilder();

        for (int i = 0; i < armyList.size(); i++) {

            UnitCardDTO unitCard = armyList.get(i);

            // add unit
            codedList.append(unitCard.getId());

            // test for and add any items
            if (!unitCard.getEquipment().isEmpty()) {

                codedList.append(":");
                String encodedItems = encodeItems(unitCard.getEquipment());
                codedList.append(encodedItems);
            }

            // get second sub faction names
            List<String> secondSubFactionNamesForFaction = getSecondSubFactionNamesForFaction(unitCard.getFaction());
            // get second sub faction data
            List<SecondSubFactionDTO> secondSubFactionDTOsForFaction = getSecondSubFactionDTOsForFaction(unitCard.getFaction());

            // test if unit has a second subFaction
            if (secondSubFactionNamesForFaction.contains(unitCard.getSecondSubFaction())) {
                codedList.append("-");

                Optional<Integer> optional = secondSubFactionDTOsForFaction
                        .stream() //
                        .filter(dto -> dto.getSecondSubFaction().equals(unitCard.getSecondSubFaction()))
                        .map(SecondSubFactionDTO::getId)
                        .findFirst();

                if (optional.isPresent()) {
                    codedList.append(optional.get());
                } else {
                    throw new AppException("cannot encode second sub faction - "
                            + unitCard.getSecondSubFaction()
                            + " not found by encoder", HttpStatus.NOT_FOUND);
                }
            }

            // test for end of list
            if (i != armyList.size() - 1) {
                codedList.append(".");
            }
        }
        return codedList.toString();
    }

    /**
     * Function decodes a string built according to the rules and returns the correct
     * list of UnitCardDTO objects.
     *
     * @param encodedList String that encodes an army list.
     * @param faction     name of the faction.
     * @return a list of UnitCardDTOs,
     */
    @Override
    public List<UnitCardDTO> decode(String encodedList, String faction) {

        String[] encodedUnits = encodedList.split("[.]");
        List<UnitCardDTO> result = new ArrayList<>();

        for (String encodedUnit : encodedUnits) {

            // store results
            String secondSubFaction = "";
            List<ItemCardDTO> itemList = new ArrayList<>();

            // test for second sub faction
            if (encodedUnit.contains("-")) {
                String[] parts = encodedUnit.split("[-]");
                encodedUnit = parts[0];
                secondSubFaction = decodeSecondSubFaction(parts[1], faction);
            }

            // test for items
            if (encodedUnit.contains(":")) {
                String[] parts = encodedUnit.split("[:]");

                encodedUnit = parts[0];
                itemList = decodeItems(parts[1], faction);
            }

            UnitCardDTO decodedUnit = decodeUnit(encodedUnit);

            // if a second sub faction was encoded, add it
            if (!secondSubFaction.equals("")) {
                decodedUnit.setSecondSubFaction(secondSubFaction);
            }
            decodedUnit.setEquipment(itemList);

            setEquipmentFlags(decodedUnit);
            result.add(decodedUnit);
        }

        return result;
    }


    private List<String> getSecondSubFactionNamesForFaction(String selectedFaction) {

        return secondSubFactionService.returnAll()
                .stream()//
                .filter(dto -> dto.getFaction().equals(selectedFaction))
                .map(SecondSubFactionDTO::getSecondSubFaction)
                .collect(Collectors.toList());
    }

    private List<SecondSubFactionDTO> getSecondSubFactionDTOsForFaction(String selectedFaction) {

        return secondSubFactionService.returnAll()
                .stream()//
                .filter(dto -> dto.getFaction().equals(selectedFaction)).collect(Collectors.toList());
    }

    /**
     * Method sets the equipmentTypes flags to the correct value, similar to the front end.
     *
     * @param unit UnitCardDTO
     */
    private void setEquipmentFlags(UnitCardDTO unit) {

        EquipmentTypes equipmentTypes = new EquipmentTypes();

        for (ItemCardDTO dto : unit.getEquipment()) {
            if (dto.getEveryElement()) {
                equipmentTypes.setUnit(true);
            } else if (dto.getRequiresBanner()) {
                equipmentTypes.setBanner(true);
            } else if (dto.getRequiresMusician()) {
                equipmentTypes.setInstrument(true);
            } else if (dto.getItemType().equals("fortifications")) {
                equipmentTypes.setFortifications(true);
            } else {
                equipmentTypes.setMagicItem(true);
            }
        }

        unit.setEquipmentTypes(equipmentTypes);
    }


    /**
     * Method encodes items by turning them into a simple comma separated string of IDs.
     *
     * @param items List<ItemCardDTO>
     * @return a String containing comma-separated values.
     */
    private String encodeItems(List<ItemCardDTO> items) {
        StringBuilder codedItems = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            codedItems.append(items.get(i).getId());

            // test for end of list
            if (i != items.size() - 1) {
                codedItems.append(",");
            }
        }
        return codedItems.toString();
    }


    private String decodeSecondSubFaction(String encodedSecondSubFaction, String faction) {
        List<String> foundSecondSubFaction = secondSubFactionService
                .returnAll()
                .stream()
                .filter(dto -> dto.getFaction().equals(faction))
                .filter(dto -> dto.getId().toString().equals(encodedSecondSubFaction))
                .map(SecondSubFactionDTO::getSecondSubFaction)
                .collect(Collectors.toList());

        return foundSecondSubFaction.get(0);
    }

    /**
     * @param encodedItems String following the rules laid out in the class comment.
     * @param faction      String faction name.
     * @return List<ItemCardDTO>
     */
    private List<ItemCardDTO> decodeItems(String encodedItems, String faction) {

        List<ItemCard> allFactionItemCards = itemCardService.findAllFactionItemCards(faction);
        List<ItemCardDTO> result = new ArrayList<>();

        String[] itemIds = encodedItems.split("[,]");

        for (String id : itemIds) {

            Optional<ItemCard> optional = allFactionItemCards
                    .stream() //
                    .filter(card -> card.getId().equals(Integer.decode(id)))
                    .findFirst();

            if (optional.isPresent()) {
                result.add(itemCardMapper.entityToDTO(optional.get()));
            } else {
                throw new AppException("cannot decode stored item - item" + encodedItems + " not found by decoder", HttpStatus.NOT_FOUND);
            }
        }

        return result;
    }

    /**
     * @param encodedUnit String following the rules laid out in the class description.
     * @return List<ItemCardDTO>
     */
    private UnitCardDTO decodeUnit(String encodedUnit) {

        List<UnitCard> allFactionUnitCards = unitCardService.findAllUnitEntities();

        Optional<UnitCard> optional = allFactionUnitCards.stream()
                .filter(card -> card.getId().equals(Integer.decode(encodedUnit)))
                .findFirst();

        if (optional.isPresent()) {
            UnitCardDTO unitCardDTO = unitCardMapper.entityToDto(optional.get());
            unitCardDTO.setMaxHitpointCounter();
            return unitCardDTO;
        } else {
            throw new AppException("cannot decode stored army list - unit" + encodedUnit + " not found by decoder", HttpStatus.NOT_FOUND);
        }
    }

}
