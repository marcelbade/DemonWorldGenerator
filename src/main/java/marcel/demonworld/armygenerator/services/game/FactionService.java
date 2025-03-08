package marcel.demonworld.armygenerator.services.game;


import marcel.demonworld.armygenerator.dto.game.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.repositories.game.FactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactionService {

    @Autowired
    private FactionRepository repo;

    public List<FactionDTO> returnAll() {
        return repo.findAll();
    }

}
