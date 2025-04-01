package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ArmyListDTO {

    private String userName;
    private String faction;
    private String listName;
    private List<UnitCardDTO> list;
    private String eventName;
    private String teamName;
    private Date creationDate;
    private List<String> userWithAccess;

}
