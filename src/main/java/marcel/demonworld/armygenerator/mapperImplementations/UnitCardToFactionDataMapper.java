package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.*;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.SubFactionDTO;
import marcel.demonworld.armygenerator.enums.GameEnums;
import marcel.demonworld.armygenerator.services.game.FactionService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Class creates FactionDataDTO objects via its sole public factory method. See FactionDataDTO class for more info about it.
 */

@Component
@Primary
public class UnitCardToFactionDataMapper implements marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapper {

    @Autowired
    FactionService factionService;

    /**
     * Method maps information from unitCardDTO, AllianceAndAlternativesDTO and SecondSubFactionDTO to FactionDataDTO
     * and returns a list w. one FactionDataDTO per faction.
     *
     * @param unitList                      list of unitCardDTO objects
     * @param allAllianceAndAlternativeDTOs list of AllianceAndAlternativesDTO objects.
     * @param secondSubFactionList          list of SecondSubFactionDTO objects.
     * @return a list containing one FactionTDO object for every in-game faction.
     */
    @Override
    public List<FactionDataDTO> unitCardToFactionData(List<UnitCardDTO> unitList,
                                                      List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs,//
                                                      List<SecondSubFactionDTO> secondSubFactionList) {

        List<FactionDataDTO> resultingFactionDataDTOList = new ArrayList<>();

        setMaxCounterForAllUnits(unitList);

        // get all distinct faction names
        Set<String> factionNames = factionService.returnAll().stream().map(FactionDTO::getFactionName).collect(Collectors.toSet());

        // build factionDataDTOs for every faction
        for (String factionName : factionNames) {
            FactionDataDTO factionDataDTO = new FactionDataDTO();

            factionDataDTO.setFactionName(factionName);

            // add subfactions and units!
            factionDataDTO.setSubFactions(createSubFactionDTOs(factionName, unitList, allAllianceAndAlternativeDTOs, false));

            // set alternative lists, if the faction has them
            AllianceAndAlternativesDTO allyAndAlts = findAlly(factionName, allAllianceAndAlternativeDTOs);
            factionDataDTO.setHasAlternativeLists(allyAndAlts.getHasAlternativeLists());
            factionDataDTO.setNumberOfAlternativeArmySelections(allyAndAlts.getNumberOfChoices());
            factionDataDTO.setAlternativeOptions(getAlternativeSubFactionsForFaction(factionName, allAllianceAndAlternativeDTOs));

            String allyName = allyAndAlts.getAlly();

            // Add Ally, if the faction has one
            if (allyName.equals(GameEnums.NONE.toString())) {
                factionDataDTO.setAlly(GameEnums.NO_ALLY.toString());
                factionDataDTO.setAllySubFactions(null);
            } else {
                factionDataDTO.setAlly(allyName);
                factionDataDTO.setAllySubFactions(createSubFactionDTOs(allyName, unitList, allAllianceAndAlternativeDTOs, true));
            }

            factionDataDTO.setHasSecondSubFactions(hasSecondSubFactions(factionName, secondSubFactionList));
            factionDataDTO.setSecondSubFactionDTOS(addSecondSubFactionsForFaction(factionName, secondSubFactionList));
            factionDataDTO.setSubFactionsIneligibleFor2ndSubFactions(findSubFactionsIneligibleFor2ndSubFaction(unitList, createSubFactionListForFaction(factionName, unitList)));

            factionDataDTO.setAllyIsAlternativeOption(findIfAllyIsAlternativeFaction(allAllianceAndAlternativeDTOs, factionName, allyName));

            resultingFactionDataDTOList.add(factionDataDTO);
        }

        return resultingFactionDataDTOList;
    }


    /**
     * Method sets the correct value for the MaxHitpointCounter property. This property represents the maximum
     * hit points (# of elements x hit points per element ) for a unit card and is used in the loss calculator feature
     * of the UI.
     *
     * @param unitList List<unitCard>
     * @return A list of unitCard objects with the property set.
     */
    private List<UnitCardDTO> setMaxCounterForAllUnits(List<UnitCardDTO> unitList) {
        unitList.forEach(UnitCardDTO::setMaxHitpointCounter);
        return unitList;
    }


    /**
     * Method checks whether if the ally of a faction is also one of the alternative sub factions the player can pick from.
     * See the rule book for the Dwarves for an example.
     *
     * @param allAllianceAndAlternativeDTOs List<AllianceAndAlternativesDTO>
     * @param factionName                   String
     * @param allyName                      String
     * @return true, if the faction's ally is also an alternative sub faction.
     */
    private boolean findIfAllyIsAlternativeFaction(List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs, String factionName, String allyName) {
        boolean result = false;
        JSONArray alternatives = (JSONArray) getAlternativeSubFactionsForFaction(factionName, allAllianceAndAlternativeDTOs).get("subFactions");

        for (Object alt : alternatives) {
            result = alt.equals(allyName);
        }
        return result;
    }

    /**
     * Method creates a list of subfaction DTOs that are part of the nested FactionDataDTO objects.
     *
     * @param factionName                   String, name of the faction
     * @param units,                        List<UnitCardDTO>
     * @param allAllianceAndAlternativeDTOs List<AllianceAndAlternativesDTO>
     * @param isAlly                        boolean
     * @return a complete and unordered list of all <SubFactionDTO> objects for the given faction.
     */
    private List<SubFactionDTO> createSubFactionDTOs(String factionName, //
                                                     List<UnitCardDTO> units,
                                                     List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs,
                                                     boolean isAlly) {

        List<SubFactionDTO> result = new ArrayList<>();
        List<String> distinctSubFactions = createSubFactionListForFaction(factionName, units);
        List<String> alternativeSubFactions = new ArrayList<>();

        if (!isAlly) {
            List<JSONObject> alternativeSubFactionsObject = allAllianceAndAlternativeDTOs
                    .stream().filter(dto -> dto.getFaction().equals(factionName))
                    .map(AllianceAndAlternativesDTO::getAlternativeSubFactions)
                    .collect(Collectors.toList());

            @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
            List<String> temp = (List<String>) alternativeSubFactionsObject
                    .get(0)
                    .getOrDefault("subFactions", new ArrayList<String>());

            alternativeSubFactions = temp;

        }

        for (String subFaction : distinctSubFactions) {

            SubFactionDTO dto = new SubFactionDTO();
            dto.setName(subFaction);
            dto.setUnits(findUnitsForSubFaction(factionName, subFaction, units));
            dto.setAlternativeListOption(alternativeSubFactions.contains(subFaction));

            result.add(dto);
        }
        return result;
    }


    /**
     * Method creates a list of all distinct sub faction names for the passed faction name.
     *
     * @param factionName String
     * @param units       List<UnitCardDTO>
     * @return a List<String> containing all distinct sub faction names.
     */
    private List<String> createSubFactionListForFaction(String factionName, List<UnitCardDTO> units) {
        return units
                .stream()
                .filter(u -> u.getFaction().equals(factionName))
                .map(UnitCardDTO::getSubFaction).distinct()
                .collect(Collectors.toList());
    }

    /**
     * Method returns a list containing all unitCards for the passed faction
     *
     * @param factionName String
     * @param subFaction  String
     * @param units       List<UnitCardDTO>
     * @return a filtered List<UnitCardDTO> containing only the List<UnitCardDTO> with the passed faction name.
     */
    private List<UnitCardDTO> findUnitsForSubFaction(String factionName, String subFaction, List<UnitCardDTO> units) {
        return units.
                stream()
                .filter(u -> u.getFaction().equals(factionName) && u.getSubFaction().equals(subFaction))
                .collect(Collectors.toList());
    }


    /**
     * Method searches a list of AllianceAndAlternativesDTO objects. If the matching one for the passed faction is found,
     * it is returned.
     *
     * @param factionName String
     * @param allyList    List<AllianceAndAlternativesDTO>
     * @return the matching allianceAndAlternativeDTO object fot the passed faction name.
     */
    private AllianceAndAlternativesDTO findAlly(String factionName, List<AllianceAndAlternativesDTO> allyList) {
        List<AllianceAndAlternativesDTO> allianceAndAlternativeDTOS = allyList
                .stream()
                .filter(al -> al.getFaction().equals(factionName))
                .collect(Collectors.toList());
        return allianceAndAlternativeDTOS.get(0);
    }

    /**
     * Method returns the list of alternative sub factions for the passed faction.
     *
     * @param factionName                   String
     * @param allAllianceAndAlternativeDTOs List<AllianceAndAlternativesDTO>
     * @return JSONObject containing all alternative sub factions for the faction.
     */
    private JSONObject getAlternativeSubFactionsForFaction(String factionName, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs) {

        List<JSONObject> result = allAllianceAndAlternativeDTOs
                .stream()
                .filter(dto -> dto.getFaction().equals(factionName))
                .map(AllianceAndAlternativesDTO::getAlternativeSubFactions)
                .collect(Collectors.toList());
        return result.get(0);
    }


    private Boolean hasSecondSubFactions(String factionName, List<SecondSubFactionDTO> secondSubFactionList) {

        Boolean result = Boolean.FALSE;

        List<SecondSubFactionDTO> filteredForFaction = secondSubFactionList
                .stream() //
                .filter(secondSubFactionDTO -> secondSubFactionDTO.getFaction().equals(factionName))
                .collect(Collectors.toList());

        if (filteredForFaction.size() > 0) {
            result = true;

        }
        return result;
    }

    /**
     * Method finds the second sub factions for the given faction name.
     *
     * @param factionName          String
     * @param secondSubFactionList List<SecondSubFactionDTO>
     * @return a list of second sub factions filtered by faction name.
     */
    private List<SecondSubFactionDTO> addSecondSubFactionsForFaction(String factionName, List<SecondSubFactionDTO> secondSubFactionList) {

        return secondSubFactionList
                .stream() //
                .filter(secondSubFactionDTO -> secondSubFactionDTO.getFaction().equals(factionName))
                .collect(Collectors.toList());
    }


    /**
     * Method takes the list of sub factions for a faction and finds those that contain no units that are
     * eligible for a 2nd sub faction.
     *
     * @param unitList             List<UnitCardDTO>
     * @param distinctFactionNames List<String> distinctFactionNames - all sub factions for one faction.
     * @return a list of sub factions that contain no unit that is eligible for a 2nd sub faction.
     */
    private List<String> findSubFactionsIneligibleFor2ndSubFaction(List<UnitCardDTO> unitList, List<String> distinctFactionNames) {

        List<String> ineligibleSubFactions = new ArrayList<>();

        distinctFactionNames.forEach(s -> {
            List<UnitCardDTO> subFactionUnits = unitList.stream().filter(unitCardDTO -> unitCardDTO.getSubFaction().equals(s)).collect(Collectors.toList());

            // if the property IsEligibleFor2ndSubFaction is false for all elements (unitCards), then noneMatches returns true (duh)
            if (subFactionUnits.stream().noneMatch(UnitCardDTO::getIsEligibleFor2ndSubFaction)) {
                ineligibleSubFactions.add(s);
            }
        });

        return ineligibleSubFactions;
    }


}// end of class


