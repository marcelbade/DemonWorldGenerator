package marcel.demonworld.armygenerator.services;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.repositories.ArmyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectArmyService {

    ArmyRepository repo;


    //TODO[TO NOTES]: for some reason this creates a Nulöl Pointer UNLESS you autowire repo in the constructor?!?!
    @Autowired
    public SelectArmyService(ArmyRepository repo) {
        this.repo = repo;
    }


    public List<UnitCard> returnArmy(String faction) {
        return repo.findAll().stream().filter(uc -> uc.getFaction().equals(faction)).collect(Collectors.toList());
    }

    public List<UnitCard> returnAll() {
        return repo.findAll();
    }
}
