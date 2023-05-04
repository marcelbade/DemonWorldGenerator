package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;
import marcel.demonworld.armygenerator.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Controller class for items. returns the itens available in the game, either unfiltered or filltered by faction and/or unit type:
 * NOTE: FACTION FILTERING INCLUDES GENERIC!
 */
@RestController
public class ItemController {

    @Autowired
    ItemService service;


    /**
     * Returns every in game item.
     *
     * @return unfiltered itemCard list
     */
    @GetMapping("/items")
    public List<ItemCard> getEveryItem() {
        return service.returnAllItems();
    }

    /**
     * Returns all items for one faction plus generics.
     *
     * @param faction the faction name
     * @return filtered itemCard list.
     */
    @GetMapping("/items/faction")
    public List<ItemCard> getItemsOfFaction(@PathVariable String faction) {
        //todo: remove
        System.out.println("/items/faction" + faction);
        return service.returnAllItemsOfFaction(faction);
    }

    /**
     * Returns all items available for one type of unit, e.g., cavalry.
     *
     * @param unitType the unitType name
     * @return filtered itemCard list.
     */
    @GetMapping("/items/unitType")
    public List<ItemCard> getItemsForUnitType(@PathVariable String unitType) {
        return service.returnAllItemsForUnitType(unitType);
    }

    /**
     * Returns all items available for one faction AND one type of unit, e.g., cavalry.
     *
     * @param faction  the faction name
     * @param unitType the unitType name
     * @return
     */
    @GetMapping("/items/faction/unitType")
    public List<ItemCard> getItemsForUnitTypeAndFaction(@PathVariable String faction, @PathVariable String unitType) {
        return service.returnAllItemsForUnitTypeandFaction(faction, unitType);
    }
}
