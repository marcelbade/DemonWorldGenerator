package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.entities.game.ItemCard;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import marcel.demonworld.armygenerator.mapperImplementations.game.ItemCardMapperImplementation;
import marcel.demonworld.armygenerator.repositories.game.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    @Autowired
    ItemRepository repo;

    @Autowired
    ItemCardMapperImplementation mapper;

    /**
     * Method returns all items in the game.
     *
     * @return List<ItemCard>
     */
    public List<ItemCardDTO> returnAll() {

        List<ItemCard> all = repo.findAll();

        return all.stream().map(a -> mapper.itemCardToItemCardDto(a)).collect(Collectors.toList());
    }

}










