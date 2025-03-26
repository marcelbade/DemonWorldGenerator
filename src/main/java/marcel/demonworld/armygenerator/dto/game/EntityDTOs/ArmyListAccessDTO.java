package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Data;
import marcel.demonworld.armygenerator.entities.ArmyList;

@Data
@Builder
public class ArmyListAccessDTO {

    private Long id;

    private String sharedWithUser;

    private ArmyList sharedList;

}
