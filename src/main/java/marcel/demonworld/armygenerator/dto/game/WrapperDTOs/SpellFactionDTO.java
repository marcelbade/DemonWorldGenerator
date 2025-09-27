package marcel.demonworld.armygenerator.dto.game.WrapperDTOs;


import lombok.Builder;
import lombok.Getter;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;

import java.util.List;

@Getter
@Builder
public class SpellFactionDTO {

    private String factionName;
    private List<SpellDTO> factionSpells;

}
