package marcel.demonworld.armygenerator.services.game;

import marcel.demonworld.armygenerator.dto.game.UnitCard;
import marcel.demonworld.armygenerator.repositories.game.ArmyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectArmyService {

    @Autowired
    ArmyRepository repo;

    /**
     * Method returns all units in the game.
     * @return List<UnitCard>
     */
    public List<UnitCard> returnAll(){
        return repo.findAll();
    }
}
