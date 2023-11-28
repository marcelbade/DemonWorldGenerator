package marcel.demonworld.armygenerator.services;


import marcel.demonworld.armygenerator.dto.FactionsDTO.FactionDTO;
import marcel.demonworld.armygenerator.repositories.FactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactionService {

    @Autowired
    FactionRepository repo;

    public List<FactionDTO> returnAll() {
        return repo.findAll();
    }

}
