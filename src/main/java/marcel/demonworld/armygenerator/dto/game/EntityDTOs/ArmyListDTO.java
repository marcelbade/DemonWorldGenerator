package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmyListDTO {


    private String userName;
    private String faction;
    private String listName;
    private List<UnitCardDTO> list;
    private Boolean isVisibleToOrganizer;
    private String eventName;

}
