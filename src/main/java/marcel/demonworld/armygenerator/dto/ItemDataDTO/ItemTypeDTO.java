package marcel.demonworld.armygenerator.dto.ItemDataDTO;


import lombok.Data;
import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;

import java.util.List;

@Data
public class ItemTypeDTO {

    private String typeName;
    private List<ItemCard> items;
}
