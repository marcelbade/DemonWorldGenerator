package marcel.demonworld.armygenerator.services;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.repositories.ArmyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for the unitCard class. Returns unit cards and armies contained in the game.
 */
@Service
public class SelectArmyService {

    ArmyRepository repo;

    //TODO[TO NOTES]: for some reason this creates a Null Pointer UNLESS you autoWire the repo in the constructor?!?!
    @Autowired
    public SelectArmyService(ArmyRepository repo) {
        this.repo = repo;
    }

    /**
     * Method returns all unitCards for one faction from the DB
     *
     * @param faction - the selected faction
     * @return a list of unit cards from the selected faction
     */
    public List<UnitCard> returnArmy(String faction) {
        return repo.findAll().stream().filter(uc -> uc.getFaction().equals(faction)).collect(Collectors.toList());
    }

    /**
     * Method returns all unitCards in the game.
     *
     * @return All units in DB
     */
    public List<UnitCard> returnAll() {
        return repo.findAll();
    }

    /**
     * Method returns the names of all factions in the game.
     *
     * @return a String Array of all the faction names in the game.
     */
    public String[] returnArmyNames() {
        //TODO: add to notes -> toArray(String[]::new)
        return repo.findAll().stream().map(UnitCard::getFaction).distinct().sorted().toArray(String[]::new);
    }
}



