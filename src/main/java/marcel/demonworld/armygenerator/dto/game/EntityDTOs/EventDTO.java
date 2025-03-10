package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.entities.auth.User;
import marcel.demonworld.armygenerator.entities.game.ArmyList;

import java.util.List;

@Getter
@Setter
@Builder
public class EventDTO {

    private Long id;
    private String eventName;
    private List<ArmyList> armyLists;
    private User organizer;

}

