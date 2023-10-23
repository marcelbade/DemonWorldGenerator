package marcel.demonworld.armygenerator.services;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternatives;
import marcel.demonworld.armygenerator.repositories.AlliesAndAlternativesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllyAndAlternativesService {

    @Autowired
    AlliesAndAlternativesRepository repo;

    public List<AllianceAndAlternatives> returnAll() {
        return repo.findAll();
    }
}
