package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
@Builder
public class AllianceAndAlternativesDTO {

    private Integer id;

    private String faction;

    private String ally;

    private Boolean hasAlternativeLists;

    private Integer numberOfChoices;

    private JSONObject alternativeSubFactions;

}
