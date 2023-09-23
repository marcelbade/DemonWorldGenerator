package marcel.demonworld.armygenerator.MapperImplementations;

import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionData;
import marcel.demonworld.armygenerator.dto.FactionDataDTO.SubfactionDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapperInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UnitCardToFactionDataMapper implements UnitCardToFactionDataMapperInterface {

    @Override
    public List<FactionData> unitCardToFactionData(List<UnitCard> list) {

        List<FactionData> result = new ArrayList<>();

        // get all distinct faction names
        Set<String> factionNames = createSetOfDistinctFactionNames(list);

        for (String factionName : factionNames) {
            FactionData data = new FactionData();

            List<UnitCard> factionUnits = createFactionUnitLists(factionName, list);
            List<SubfactionDTO> subFactions = createSubFactionListForFaction(factionUnits);

            data.setFactionName(factionName);
            data.setSubFactions(subFactions);
            data.setUnits(factionUnits);

            result.add(data);
        }
        return result;
    }

    // Method creates set of all faction names in the game.
    private Set<String> createSetOfDistinctFactionNames(List<UnitCard> list) {
        return list.stream().map(UnitCard::getFaction).collect(Collectors.toSet());
    }

    // Method creates a list containing subFaction objects for one faction.
    private List<SubfactionDTO> createSubFactionListForFaction(List<UnitCard> units) {
        List<String> onlyNames = units.stream().map(UnitCard::getSubFaction).distinct().collect(Collectors.toList());

        return onlyNames.stream().map(o -> new SubfactionDTO(o, "")).collect(Collectors.toList());
    }


    // Method creates a list containing all units of one faction.
    private List<UnitCard> createFactionUnitLists(String factionName, List<UnitCard> units) {
        return units.stream().filter(u -> u.getFaction().equals(factionName)).collect(Collectors.toList());
    }
}


