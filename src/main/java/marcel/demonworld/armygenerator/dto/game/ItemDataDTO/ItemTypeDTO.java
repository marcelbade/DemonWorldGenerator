package marcel.demonworld.armygenerator.dto.game.ItemDataDTO;


import lombok.Data;
import marcel.demonworld.armygenerator.dto.game.statCardDTOs.ItemCard;

import java.util.List;

@Data
public class ItemTypeDTO {

    private String typeName;
    private List<ItemCard> items;
}
