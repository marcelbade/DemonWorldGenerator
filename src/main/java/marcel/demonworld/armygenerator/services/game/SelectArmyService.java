package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import marcel.demonworld.armygenerator.mapperImplementations.game.UnitCardDtoToUnitCardMapper;
import marcel.demonworld.armygenerator.repositories.game.ArmyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SelectArmyService {

    @Autowired
    ArmyRepository repo;

    @Autowired
    UnitCardDtoToUnitCardMapper mapper;

    /**
     * Method returns all units in the game.
     *
     * @return List<UnitCard>
     */
    public List<UnitCardDTO> returnAll() { //
        List<UnitCard> all = repo.findAll();

        return all.stream().map(a -> mapper.UnitCardToUnitCardDto(a)).collect(Collectors.toList());
    }
}
