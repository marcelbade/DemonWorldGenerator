package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.dto.ItemDataDTO.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;

import java.util.List;

public interface ItemCardToItemDataDTOMapperInterface {
    ItemDataDTO unitCardToFactionData(List<ItemCard> itemList, List<FactionDTO> allFactions);




}
