package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.game.Event;
import org.json.simple.JSONObject;

@Setter
@Getter
@Builder
public class ArmyListDTO {

    private Long id;
    private UserDTO user;
    private String listName;
    private JSONObject list;
    private Boolean isVisibleToOrganizer;
    private Event event;

}
