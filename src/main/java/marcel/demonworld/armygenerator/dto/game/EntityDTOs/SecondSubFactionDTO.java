package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecondSubFactionDTO {

    private String faction;
    private String secondSubFaction;

}
