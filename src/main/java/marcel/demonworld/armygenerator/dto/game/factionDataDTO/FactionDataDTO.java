package marcel.demonworld.armygenerator.dto.game.factionDataDTO;

import lombok.Data;
import org.json.simple.JSONObject;

import java.util.List;

@Data
public class FactionDataDTO {
    private String factionName;
    private List<SubFactionDTO> subFactions;
    private String Ally;
    private List<SubFactionDTO> allySubFactions;
    private Boolean hasAlternativeLists;
    private Integer numberOfAlternativeArmySelections;
    private JSONObject alternativeOptions;
    private Boolean AllyIsAlternativeOption;
}
