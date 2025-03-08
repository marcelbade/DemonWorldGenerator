package marcel.demonworld.armygenerator.dto.game;


import lombok.Data;

import java.util.List;

@Data
public class ItemTypeDTO {

    private String typeName;
    private List<ItemCard> items;
}
