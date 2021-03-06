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
    ItemRepository repo;

    /**
     * returns all items of one faction, including generic!
     *
     * @param faction: army faction in the game
     * @return all items that belong to a faction
     */
    public List<ItemCard> returnAllItemsOfFaction(String faction) {
        return repo.findAll().stream().filter(i -> i.getFaction().equals(faction)).collect(Collectors.toList());
    }

    /**
     * @param unitType the unittype of the selected unit card
     * @return A list of item cards filtered for the selected unit type
     */
    public List<ItemCard> returnAllItemsForUnitType(String unitType) {
        return repo.findAll().stream().filter(i -> i.getUsedBy().equalsIgnoreCase(unitType)).collect(Collectors.toList());

    }

    /**
     * @param faction
     * @param unitType
     * @return
     */
    public List<ItemCard> returnAllItemsForUnitTypeandFaction(String faction, String unitType) {
        //TODO: FINISH THIS :)
        return repo.findAll().stream().filter(i -> i.getUsedBy() == unitType && i.getFaction().equals(faction)).collect(Collectors.toList());
    }
}














