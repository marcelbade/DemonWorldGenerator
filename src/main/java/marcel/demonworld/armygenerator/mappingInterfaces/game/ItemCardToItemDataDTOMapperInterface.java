package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.game.ItemCard;

import java.util.List;

public interface ItemCardToItemDataDTOMapperInterface {
    ItemDataDTO unitCardToFactionData(List<ItemCard> itemList, List<FactionDTO> allFactions);




}
