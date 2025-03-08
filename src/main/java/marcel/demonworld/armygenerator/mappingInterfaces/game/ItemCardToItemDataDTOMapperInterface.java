package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.ItemDataDTO.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.game.statCardDTOs.ItemCard;

import java.util.List;

public interface ItemCardToItemDataDTOMapperInterface {
    ItemDataDTO unitCardToFactionData(List<ItemCard> itemList, List<FactionDTO> allFactions);




}
