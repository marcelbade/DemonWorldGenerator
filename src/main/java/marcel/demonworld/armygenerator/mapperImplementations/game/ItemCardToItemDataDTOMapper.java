package marcel.demonworld.armygenerator.mapperImplementations.game;


import marcel.demonworld.armygenerator.Enums.GameEnums;
import marcel.demonworld.armygenerator.dto.game.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.game.ItemFactionDTO;
import marcel.demonworld.armygenerator.dto.game.ItemTypeDTO;
import marcel.demonworld.armygenerator.dto.game.ItemCard;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ItemCardToItemDataDTOMapperInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class ItemCardToItemDataDTOMapper implements ItemCardToItemDataDTOMapperInterface {

    @Override
    public ItemDataDTO unitCardToFactionData(List<ItemCard> allItems, List<FactionDTO> AllFactions) {


        Set<String> itemTypes = getDistinctItemTypes(allItems);
        Set<String> factionNames = getDistinctFactionNames(AllFactions);
        factionNames.add(GameEnums.GENERIC.toString());

        ItemDataDTO result = new ItemDataDTO();

        for (String faction : factionNames) {

            ItemFactionDTO itemFactionDTO = new ItemFactionDTO();
            itemFactionDTO.setFactionName(faction);

            for (String itemType : itemTypes) {
                List<ItemCard> filteredItems = filterItemsForFactionAndType(faction, itemType, allItems);
                ItemTypeDTO itemTypeDTO = buildItemTypeDTO(itemType, filteredItems);

                // skip empty item type groups
                if (!itemTypeDTO.getItems().isEmpty()) {
                    itemFactionDTO.addToGroupsOfFactionItemsByType(itemTypeDTO);
                }
            }
            result.addItemFactionDTOToList(itemFactionDTO);
        }
        return result;
    }


    private Set<String> getDistinctFactionNames(List<FactionDTO> factions) {
        return factions.stream().map(FactionDTO::getFactionName).collect(Collectors.toSet());
    }

    private Set<String> getDistinctItemTypes(List<ItemCard> items) {
        return items.stream().map(ItemCard::getItemType).collect(Collectors.toSet());
    }

    private List<ItemCard> filterItemsForFactionAndType(String faction, String itemType, List<ItemCard> allItems) {
        return allItems.stream()
                .filter(i -> i.getItemType().equals(itemType) && (i.getFaction().equals(faction) || i.getFaction().equals(GameEnums.GENERIC.toString())))
                .collect(Collectors.toList());
    }


    private ItemTypeDTO buildItemTypeDTO(String itemType, List<ItemCard> items) {

        ItemTypeDTO result = new ItemTypeDTO();
        result.setTypeName(itemType);
        result.setItems(items);
        return result;
    }

}
