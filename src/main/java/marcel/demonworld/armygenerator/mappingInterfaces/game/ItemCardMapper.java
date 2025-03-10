package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.entities.game.ItemCard;

public interface ItemCardMapper {

    ItemCardDTO entityToDTO(ItemCard itemCard);

    ItemCard DtoToEntity(ItemCardDTO itemCardDTO);

}
