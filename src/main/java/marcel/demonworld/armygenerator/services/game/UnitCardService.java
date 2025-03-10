package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import marcel.demonworld.armygenerator.mapperImplementations.game.unitCardMapperImplementation;
import marcel.demonworld.armygenerator.repositories.game.UnitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UnitCardService {

    @Autowired
    private final UnitCardRepository repo;

    @Autowired
    private final unitCardMapperImplementation mapper;

    /**
     * Method returns all units in the game.
     *
     * @return List<UnitCard>
     */
    public List<UnitCardDTO> returnAll() { //
        List<UnitCard> all = repo.findAll();

        return all.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    public void createNewUnit(UnitCardDTO newUnit) {
        repo.save(mapper.dtoToEntity(newUnit));
    }

}
