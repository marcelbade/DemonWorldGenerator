package marcel.demonworld.armygenerator.dto.game.WrapperDTOs;


import lombok.Data;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;

import java.util.List;

@Data
public class ItemTypeDTO {

    private String typeName;
    private List<ItemCardDTO> items;
}
