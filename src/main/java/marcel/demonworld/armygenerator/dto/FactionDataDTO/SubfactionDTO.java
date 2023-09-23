package marcel.demonworld.armygenerator.dto.FactionDataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubfactionDTO {

    private String name;
    final private Boolean blocked = Boolean.FALSE;
    private String blockMessage;
}
