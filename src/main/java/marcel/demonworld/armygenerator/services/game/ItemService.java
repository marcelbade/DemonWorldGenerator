package marcel.demonworld.armygenerator.services.game;

import marcel.demonworld.armygenerator.dto.game.ItemCard;
import marcel.demonworld.armygenerator.repositories.game.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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










