package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Setter
@Getter
@Builder
public class AllianceAndAlternativesDTO {

    private Integer id;

    private String faction;

    private String ally;

    private Boolean hasAlternativeLists;

    private Integer numberOfChoices;

    private JSONObject alternativeSubFactions;

}
