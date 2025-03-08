package marcel.demonworld.armygenerator.services.game;

import marcel.demonworld.armygenerator.dto.game.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.repositories.game.AlliesAndAlternativesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllyAndAlternativesService {

    @Autowired
    AlliesAndAlternativesRepository repo;

    public List<AllianceAndAlternativesDTO> returnAll() {
        return repo.findAll();
    }
}
