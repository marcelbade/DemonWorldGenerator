package marcel.demonworld.armygenerator.Encoding;

import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.EquipmentTypes;
import marcel.demonworld.armygenerator.entities.ItemCard;
import marcel.demonworld.armygenerator.entities.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.ItemCardMapper;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardMapper;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ArmyList objects have the problem, that the list property is a nested list of objects. I.e., it is a list containing
 * at least one UnitCardDTO object and each of the UnitCard objects can contain a list of
 * containing least one ItemCardDTO object. Such a nested structure is, to be uncouth,
 * a pain in the butt to persist in SQL. To avoid the hassle and added complexity of writing it into a JSON field,
 * the list is instead encoded as a simple string. Every UnitCardDTO and ItemDTO is uniquely represented by its ID.
 * Individual unitCard IDs are separated by a dot. If a UnitCard contains a list of one or more ItemCardDTOs,
 * then the ItemCard list is announced by a colon, is comma-separated and ends with a dot.
 * If a unitCard has a second sub faction, then the second sub faction is separated from the unit Card id
 * with a dash. (Currently in the game this applies only to the Thain faction, see the rule book for any questions)
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
    UnitCardMapper unitCardMapper;

    @Autowired
    ItemCardMapper itemCardMapper;


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

            // encode unit with its equipment
            codedList.append(armyList.get(i).getId());
            if (!armyList.get(i).getEquipment().isEmpty()) {

                codedList.append(":");
                String encodedItems = encodeItems(armyList.get(i).getEquipment());
                codedList.append(encodedItems);
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

        List<UnitCardDTO> decodedArmyList = new ArrayList<>();


        for (String encodedUnit : encodedUnits) {

            if (encodedUnit.contains(":")) {
                String[] parts = encodedUnit.split("[:]");

                UnitCardDTO unit = decodeUnit(parts[0]);

                unit.setEquipment(decodeItems(parts[1], faction));

                setEquipmentFlags(unit);
                decodedArmyList.add(unit);
            } else {
                decodedArmyList.add(decodeUnit(encodedUnit));
            }
        }
        return decodedArmyList;
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

    /**
     * @param encodedItems String following the rules laid out in the class description.
     * @param faction      String faction name.
     * @return List<ItemCardDTO>
     */
    private List<ItemCardDTO> decodeItems(String encodedItems, String faction) {

        List<ItemCard> allFactionItemCards = itemCardService.findAllFactionItemCards(faction);
        List<ItemCardDTO> result = new ArrayList<>();

        String[] itemIds = encodedItems.split("[,]");

        for (String id : itemIds) {

            Optional<ItemCard> optional = allFactionItemCards.stream()
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
