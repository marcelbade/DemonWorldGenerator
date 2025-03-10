package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.ItemDataDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ItemDataMapper;
import marcel.demonworld.armygenerator.services.game.FactionService;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/game")
public class ItemController {

    @Autowired
    private ItemCardService itemCardService;

    @Autowired
    private FactionService factionService;

    @Autowired
    private  ItemDataMapper itemCardToItemDataDTOMapperInterface;

    /**
     * Returns all item cards of one faction
     *
     * @return all item cards
     */
    @CrossOrigin
    @GetMapping("/allItems")
    public List<ItemCardDTO> getAllItems() {
        return itemCardService.returnAll();
    }

    /**
     * Returns a DTO that contains for every faction, all items ordered by type. Generic Items are they own "faction".
     *
     * @return a custom DTO.
     */
    @CrossOrigin
    @GetMapping("/itemDTOs")
    public ItemDataDTO getAllItemDTOs() {
        List<ItemCardDTO> allItems = itemCardService.returnAll();
        List<FactionDTO> allFactions = factionService.returnAll();

        return itemCardToItemDataDTOMapperInterface.unitCardToFactionData(allItems, allFactions);
    }

}
