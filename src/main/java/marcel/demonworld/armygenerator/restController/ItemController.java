package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.dto.ItemDataDTO.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;
import marcel.demonworld.armygenerator.mappingInterfaces.ItemCardToItemDataDTOMapperInterface;
import marcel.demonworld.armygenerator.services.FactionService;
import marcel.demonworld.armygenerator.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    FactionService factionService;

    @Autowired
    ItemCardToItemDataDTOMapperInterface itemCardToItemDataDTOMapperInterface;

    /**
     * Returns all item cards of one faction
     *
     * @return all item cards
     */
    @CrossOrigin
    @GetMapping("/items")
    public List<ItemCard> getAllItems() {
        return itemService.returnAll();
    }

    /**
     * Returns a DTO that contains for every faction, all items ordered by type. Generic Items are they own "faction".
     *
     * @return a custom DTO.
     */
    @CrossOrigin
    @GetMapping("/itemDTOs")
    public ItemDataDTO getAllItemDTOs() {
        List<ItemCard> allItems = itemService.returnAll();
        List<FactionDTO> allFactions = factionService.returnAll();

        return itemCardToItemDataDTOMapperInterface.unitCardToFactionData(allItems, allFactions);
    }

}
