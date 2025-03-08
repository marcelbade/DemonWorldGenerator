package marcel.demonworld.armygenerator.dto.game.ItemDataDTO;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemDataDTO {

    final private List<ItemFactionDTO> factionItems;

    public ItemDataDTO() {
        this.factionItems = new ArrayList<>();
    }

    public void addItemFactionDTOToList(ItemFactionDTO dto) {
        factionItems.add(dto);
    }

}
