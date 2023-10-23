package marcel.demonworld.armygenerator.MapperImplementations;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternatives;
import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionData;
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

    @Override
    public List<FactionData> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternatives> allAllianceAndAlternatives) {

        List<FactionData> result = new ArrayList<>();

        // get all distinct faction names
        Set<String> factionNames = createSetOfDistinctFactionNames(unitList);

        for (String factionName : factionNames) {
            FactionData data = new FactionData();

            List<UnitCard> factionUnits = createFactionUnitLists(factionName, unitList);
            List<String> subFactions = createSubFactionListForFaction(factionUnits);

            data.setFactionName(factionName);
            data.setSubFactions(subFactions);
            data.setUnits(factionUnits);

            AllianceAndAlternatives allyAndAlts = findAlly(factionName, allAllianceAndAlternatives);
            data.setHasAlternativeLists(allyAndAlts.getHasAlternativeLists());
            data.setNumberOfAlternativeArmySelections(allyAndAlts.getNumberOfChoices());

            String allyName = allyAndAlts.getAlly();


            if (!allyName.equals("NONE")) {

                // ally name must be added as additional sub faction
                List<String> tempList = data.getSubFactions();
                tempList.add(allyName);

                data.setSubFactions(tempList);

                List<UnitCard> allyUnits = createFactionUnitLists(allyName, unitList);
                List<String> allySubFactions = createSubFactionListForFaction(allyUnits);
                data.setAlly(allyName);
                data.setAllySubFactions(allySubFactions);
                data.setAllyUnits(allyUnits);
            } else {
                data.setAlly(null);
                data.setAllySubFactions(null);
                data.setAllyUnits(null);
            }


            result.add(data);
        }
        return result;
    }

    // Method creates set of all faction names in the game.
    private Set<String> createSetOfDistinctFactionNames(List<UnitCard> list) {
        return list.stream().map(UnitCard::getFaction).collect(Collectors.toSet());
    }

    // Method creates a list containing subFaction objects for one faction.
    private List<String> createSubFactionListForFaction(List<UnitCard> units) {
        return units.stream().map(UnitCard::getSubFaction).distinct().collect(Collectors.toList());
    }

    // Method creates a list containing all units of one faction.
    private List<UnitCard> createFactionUnitLists(String factionName, List<UnitCard> units) {
        return units.stream().filter(u -> u.getFaction().equals(factionName)).collect(Collectors.toList());
    }

    // Method finds the faction's ally.
    private AllianceAndAlternatives findAlly(String factionName, List<AllianceAndAlternatives> allyList) {
        List<AllianceAndAlternatives> allianceAndAlternatives = allyList.stream().filter(al -> al.getFaction().equals(factionName)).collect(Collectors.toList());
        return allianceAndAlternatives.get(0);
    }
}


