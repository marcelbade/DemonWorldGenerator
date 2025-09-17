package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactionColorDTO {

    private String faction;

    private Integer rgbA;

    private Integer rgbB;

    private Integer rgbC;

}
