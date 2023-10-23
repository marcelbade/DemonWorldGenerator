package marcel.demonworld.armygenerator.dto.FactionDataDTO;

import lombok.Data;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;

@Data
public class FactionData {
    private String factionName;
    private List<UnitCard> units;
    private List<String> subFactions;
    private String Ally;
    private List<UnitCard> allyUnits;
    private List<String> allySubFactions;
    private Boolean hasAlternativeLists;
    private Integer numberOfAlternativeArmySelections;
}
