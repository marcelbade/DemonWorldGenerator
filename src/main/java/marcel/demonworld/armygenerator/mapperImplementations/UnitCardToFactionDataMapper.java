package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.alliancesDTO.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.factionDataDTO.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.factionDataDTO.SubFactionDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapperInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UnitCardToFactionDataMapper implements UnitCardToFactionDataMapperInterface {

    @Override
    public List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs) {

        List<FactionDataDTO> mappingResult = new ArrayList<>();
        Set<String> factionNames = createSetOfDistinctFactionNames(unitList);

        for (String factionName : factionNames) {
            FactionDataDTO faction = new FactionDataDTO();

            faction.setFactionName(factionName);
            faction.setSubFactions(createSubFactionDTOs(factionName, unitList, allAllianceAndAlternativeDTOs, false));

            AllianceAndAlternativesDTO allyAndAlts = findAlly(factionName, allAllianceAndAlternativeDTOs);
            faction.setHasAlternativeLists(allyAndAlts.getHasAlternativeLists());
            faction.setNumberOfAlternativeArmySelections(allyAndAlts.getNumberOfChoices());
            faction.setAlternativeOptions(stringifyAlternativeListsJSON(factionName, allAllianceAndAlternativeDTOs));

            String allyName = allyAndAlts.getAlly();

            String NONE = "NONE";
            if (allyName.equals(NONE)) {
                String NO_ALLY = "NO_ALLY";
                faction.setAlly(NO_ALLY);
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

    // Method creates set of all faction names in the game.
    private Set<String> createSetOfDistinctFactionNames(List<UnitCard> list) {
        return list
                .stream()
                .map(UnitCard::getFaction)
                .collect(Collectors.toSet());
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


