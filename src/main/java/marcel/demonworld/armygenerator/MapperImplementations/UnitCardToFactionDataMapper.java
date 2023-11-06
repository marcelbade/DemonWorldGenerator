package marcel.demonworld.armygenerator.MapperImplementations;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.SubFactionDTO.SubFactionDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapperInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UnitCardToFactionDataMapper implements UnitCardToFactionDataMapperInterface {


    private final String NONE = "NONE";

    @Override
    public List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOs) {

        List<FactionDataDTO> finalResult = new ArrayList<>();
        Set<String> factionNames = createSetOfDistinctFactionNames(unitList);

        for (String factionName : factionNames) {
            FactionDataDTO faction = new FactionDataDTO();

            faction.setFactionName(factionName);
            faction.setSubFactions(createSubFactionDTOs(factionName, unitList));


            AllianceAndAlternativesDTO allyAndAlts = findAlly(factionName, allAllianceAndAlternativeDTOs);
            faction.setHasAlternativeLists(allyAndAlts.getHasAlternativeLists());
            faction.setNumberOfAlternativeArmySelections(allyAndAlts.getNumberOfChoices());

            String allyName = allyAndAlts.getAlly();

            if (!allyName.equals(NONE)) {
                faction.setAlly(allyName);
                faction.setAllySubFactions(createSubFactionDTOs(allyName, unitList));
            } else {
                faction.setAlly("NO_ALLY");
                faction.setAllySubFactions(null);
            }

            finalResult.add(faction);
        }
        return finalResult;
    }


    private List<SubFactionDTO> createSubFactionDTOs(String factionName, List<UnitCard> units) {

        List<SubFactionDTO> result = new ArrayList<>();
        List<String> distinctSubFactions = createSubFactionListForFaction(factionName, units);

        for (String subFaction : distinctSubFactions) {

            SubFactionDTO dto = new SubFactionDTO();
            dto.setName(subFaction);
            dto.setUnits(findUnitsForSubFaction(factionName, subFaction, units));
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
}


