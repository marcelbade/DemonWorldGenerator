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
     * Method returns all items in the game.
     * @return List<ItemCard>
     */
    public List<ItemCard> returnAll(){
        return repo.findAll();
    }

}










