package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.entities.game.ItemCard;
import marcel.demonworld.armygenerator.mapperImplementations.game.ItemCardMapperImplementation;
import marcel.demonworld.armygenerator.repositories.game.ItemCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemCardService {

    @Autowired
    private final ItemCardRepository repo;

    @Autowired
    private final ItemCardMapperImplementation mapper;

    /**
     * Method returns all items in the game.
     *
     * @return List<ItemCard>
     */
    public List<ItemCardDTO> returnAll() {

        List<ItemCard> all = repo.findAll();

        return all.stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    public void createNewItem(ItemCardDTO newItem) {

        Optional<ItemCard> foundItem = repo.findByNameAndFaction(newItem.getItemName(), newItem.getFaction());

        if (foundItem.isPresent()) {
            throw new AppException("unit already exists for this faction!", HttpStatus.BAD_REQUEST);
        } else {
            repo.save(mapper.DtoToEntity(newItem));
        }
    }


    public void updateItem(ItemCardDTO item) {

        repo.save(mapper.DtoToEntity(item));
    }

}










