package marcel.demonworld.armygenerator.services;


import marcel.demonworld.armygenerator.dto.AlliancesDTO.Alliance;
import marcel.demonworld.armygenerator.repositories.AlliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllyService {

    @Autowired
    AlliesRepository repo;

    public List<Alliance> returnAll() {
        return repo.findAll();
    }
}
