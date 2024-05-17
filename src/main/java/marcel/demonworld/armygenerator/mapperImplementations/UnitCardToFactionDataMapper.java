package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.Enums.GameEnums;
import marcel.demonworld.armygenerator.dto.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.dto.alliancesDTO.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.factionDataDTO.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.factionDataDTO.SubFactionDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapperInterface;
import marcel.demonworld.armygenerator.services.FactionService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UnitCardToFactionDataMapper implements UnitCardToFactionDataMapperInterface {

    @Autowired
    FactionService factionService;

    /**
     * Method maps unitCard objects to FactionDataDTO.
     *
     * @param unitList                      list of unitCard objects
     * @param allAllianceAndAlternativeDTOs list of AllianceAndAlternativesDTO objects.
     * @return a list containing one FactionTDO object for every in-game faction.
     */
    @Override
    public List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs) {

        setMaxCounterForAllUnits(unitList);

        List<FactionDataDTO> mappingResult = new ArrayList<>();
        Set<String> factionNames = factionService.returnAll().stream().map(FactionDTO::getFactionName).collect(Collectors.toSet());

        for (String factionName : factionNames) {
            FactionDataDTO faction = new FactionDataDTO();

            faction.setFactionName(factionName);
            faction.setSubFactions(createSubFactionDTOs(factionName, unitList, allAllianceAndAlternativeDTOs, false));

            AllianceAndAlternativesDTO allyAndAlts = findAlly(factionName, allAllianceAndAlternativeDTOs);
            faction.setHasAlternativeLists(allyAndAlts.getHasAlternativeLists());
            faction.setNumberOfAlternativeArmySelections(allyAndAlts.getNumberOfChoices());
            faction.setAlternativeOptions(stringifyAlternativeListsJSON(factionName, allAllianceAndAlternativeDTOs));

            String allyName = allyAndAlts.getAlly();


            if (allyName.equals(GameEnums.NONE.toString())) {
                faction.setAlly(GameEnums.NO_ALLY.toString());
                faction.setAllySubFactions(null);
            } else {
                faction.setAlly(allyName);
                faction.setAllySubFactions(createSubFactionDTOs(allyName, unitList, allAllianceAndAlternativeDTOs, true));
            }

            faction.setAllyIsAlternativeOption(findIfAllyIsAlternativeFaction(allAllianceAndAlternativeDTOs, factionName, allyName));

            mappingResult.add(faction);
        }
        return mappingResult;
    }


    private List<UnitCard> setMaxCounterForAllUnits(List<UnitCard> unitList) {
        unitList.forEach(UnitCard::setMaxCounter);
        return unitList;
    }


    private boolean findIfAllyIsAlternativeFaction(List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs, String factionName, String allyName) {
        boolean result = false;
        JSONArray alternatives = (JSONArray) stringifyAlternativeListsJSON(factionName, allAllianceAndAlternativeDTOs).get("subFactions");

        for (Object alt : alternatives) {
            result = alt.equals(allyName);
        }
        return result;
    }


    private List<SubFactionDTO> createSubFactionDTOs(String factionName, List<UnitCard> units, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs, boolean isAlly) {

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


    // Method creates a list containing subFaction objects for one faction.
    private List<String> createSubFactionListForFaction(String factionName, List<UnitCard> units) {
        return units
                .stream()
                .filter(u -> u.getFaction().equals(factionName))
                .map(UnitCard::getSubFaction).distinct()
                .collect(Collectors.toList());
    }


    private List<UnitCard> findUnitsForSubFaction(String factionName, String subFaction, List<UnitCard> units) {
        return units.
                stream()
                .filter(u -> u.getFaction().equals(factionName) && u.getSubFaction().equals(subFaction))
                .collect(Collectors.toList());
    }


    // Method finds the faction's ally.
    private AllianceAndAlternativesDTO findAlly(String factionName, List<AllianceAndAlternativesDTO> allyList) {
        List<AllianceAndAlternativesDTO> allianceAndAlternativeDTOS = allyList
                .stream()
                .filter(al -> al.getFaction().equals(factionName))
                .collect(Collectors.toList());
        return allianceAndAlternativeDTOS.get(0);
    }

    private JSONObject stringifyAlternativeListsJSON(String factionName, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs) {

        List<JSONObject> result = allAllianceAndAlternativeDTOs
                .stream()
                .filter(dto -> dto.getFaction().equals(factionName))
                .map(AllianceAndAlternativesDTO::getAlternativeSubFactions)
                .collect(Collectors.toList());
        return result.get(0);
    }

}


