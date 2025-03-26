package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;
import marcel.demonworld.armygenerator.entities.ArmyList;
import marcel.demonworld.armygenerator.entities.User;

import java.util.List;

@Data
@Builder
public class EventDTO {

    private Long id;
    private String eventName;
    private List<ArmyList> armyLists;
    private User organizer;

}

