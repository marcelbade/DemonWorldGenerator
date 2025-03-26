package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.entities.ItemCard;

public interface ItemCardMapper {

    ItemCardDTO entityToDTO(ItemCard itemCard);

    ItemCard DtoToEntity(ItemCardDTO itemCardDTO);

}
