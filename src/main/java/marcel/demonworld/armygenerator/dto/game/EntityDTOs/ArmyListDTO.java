package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@Builder
public class ArmyListDTO {

    private String creator;
    private List<String> userWithAccess;
    private List<UnitCardDTO> armyList;

}
