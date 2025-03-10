package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.entities.game.Event;
import org.json.simple.JSONObject;

@Setter
@Getter
@Builder
public class ArmyListDTO {

    private Long id;
    private String userName;
    private String armyListName;
    private JSONObject armyList;
    private Boolean isPublicForOrganizer;
    private Event event;

}
