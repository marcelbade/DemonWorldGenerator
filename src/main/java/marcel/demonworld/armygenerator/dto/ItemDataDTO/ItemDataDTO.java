package marcel.demonworld.armygenerator.dto.ItemDataDTO;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemDataDTO {

    final private List<ItemFactionDTO> factionItems;
    private ItemFactionDTO GenericItems;



    public ItemDataDTO() {
        this.factionItems = new ArrayList<>();
    }

    public void addItemFactionDTOToList(ItemFactionDTO dto) {
        factionItems.add(dto);
    }

    public void setGenericItemDTO(ItemFactionDTO genericItems) {
        this.GenericItems = genericItems;
    }

}
