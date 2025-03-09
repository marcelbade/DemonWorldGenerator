package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ListShareDTO;
import marcel.demonworld.armygenerator.entities.game.ListShare;

public interface ListShareMapper {
        ListShareDTO entityToDto(ListShare share);
}
