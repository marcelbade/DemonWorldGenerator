package marcel.demonworld.armygenerator.dto.SubFactionDTO;

import lombok.Data;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;


@Data
public class SubFactionDTO {

    private String name;
    private List<UnitCard> units;

}
