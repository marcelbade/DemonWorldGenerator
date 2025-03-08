package marcel.demonworld.armygenerator.dto.game.ItemDataDTO;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemFactionDTO {

    private String factionName;
    final private List<ItemTypeDTO> groupsOfFactionItemsByType;

    public ItemFactionDTO() {
        this.groupsOfFactionItemsByType = new ArrayList<>();
    }

    public void addToGroupsOfFactionItemsByType(ItemTypeDTO dto) {
        groupsOfFactionItemsByType.add(dto);
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
}
