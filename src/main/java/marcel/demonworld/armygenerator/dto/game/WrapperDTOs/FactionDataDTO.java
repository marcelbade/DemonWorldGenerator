package marcel.demonworld.armygenerator.dto.game.WrapperDTOs;

import lombok.Data;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SecondSubFactionDTO;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * main data structure for the army builder. Contains structured
 *
 *
 */

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
    private Boolean hasSecondSubFactions;
    private List<SecondSubFactionDTO> secondSubFactionDTOS;
    private  List<String> SubFactionsIneligibleFor2ndSubFactions;
}
