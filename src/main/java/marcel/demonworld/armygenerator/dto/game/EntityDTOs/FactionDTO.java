package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactionDTO {

    private Integer id;
    private String factionName;
}
