package marcel.demonworld.armygenerator.services;

import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;
import marcel.demonworld.armygenerator.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    private final String GENERIC = "generic";

    /**
     * returns all items in the game.
     *
     * @return a list of every item in the game.
     */
    public List<ItemCard> returnAllItems() {
        return repo.findAll();
    }


    /**
     * returns all items of one faction, PLUS generic!
     *
     * @param faction: army faction in the game
     * @return all items that belong to a faction
     */
    public List<ItemCard> returnAllItemsOfFaction(String faction) {
        return repo.findAll().stream().filter(i -> i.getFaction().equalsIgnoreCase(faction) || i.getFaction().equalsIgnoreCase(GENERIC)).collect(Collectors.toList());
    }

    /**
     * returns the items available for one unit type.
     *
     * @param unitType the unit type of the selected unit card
     * @return A list of item cards filtered for the selected unit type
     */
    public List<ItemCard> returnAllItemsForUnitType(String unitType) {
        return repo.findAll().stream().filter(i -> i.getUnitType().equalsIgnoreCase(unitType)).collect(Collectors.toList());
    }

    /**
     * returns all items available for one unit type, filtered by faction.
     *
     * @param faction
     * @param unitType
     * @return
     */
    public List<ItemCard> returnAllItemsForUnitTypeandFaction(String faction, String unitType) {
        //TODO: FINISH THIS :)
        return repo.findAll().stream().
                filter(i -> i.getUnitType() == unitType && (i.getFaction().equalsIgnoreCase(faction) || i.getFaction().equalsIgnoreCase(GENERIC))).
                collect(Collectors.toList());
    }
}














