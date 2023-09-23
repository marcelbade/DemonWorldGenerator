package marcel.demonworld.armygenerator.dto.FactionDataDTO;

import lombok.Data;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;

@Data
public class FactionData {
    private String factionName;
    private List<UnitCard> units;
    private List<SubfactionDTO> subFactions;


}
