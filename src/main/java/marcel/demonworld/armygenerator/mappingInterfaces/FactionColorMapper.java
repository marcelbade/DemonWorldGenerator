package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionColorDTO;
import marcel.demonworld.armygenerator.entities.FactionColor;

public interface FactionColorMapper {
    FactionColorDTO mapEntityToDTO(FactionColor factionColor);
}
