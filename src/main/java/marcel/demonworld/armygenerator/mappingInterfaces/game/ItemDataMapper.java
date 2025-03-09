package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;

import java.util.List;

public interface ItemDataMapper {
    ItemDataDTO unitCardToFactionData(List<ItemCardDTO> itemList, List<FactionDTO> allFactions);




}
