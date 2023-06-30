package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;
import marcel.demonworld.armygenerator.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * Returns all item cards of one faction
     *
     * @return all item cards
     */
    @GetMapping("/items")
    public List<ItemCard> getAllItems() {
        return  itemService.returnAll();
    }



}
