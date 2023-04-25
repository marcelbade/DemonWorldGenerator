package marcel.demonworld.armygenerator.services;

import lombok.extern.slf4j.Slf4j;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.repositories.ArmyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for the unitCard class. Returns unit cards and armies contained in the game.
 */
@Service
@Slf4j
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
        return repo.findAll().stream().filter(uc -> uc.getFaction().equalsIgnoreCase(faction)).collect(Collectors.toList());
    }


    private UnitCard enrichUnitData(UnitCard uC) {
        return uC;
    }

    /**
     * Method returns all unitCards in the game. unitCards are sorted Alphabetically. First by faction, then subFaction, then name.
     *
     * @return All units in DB
     */
    public List<UnitCard> returnAll() {
        List<UnitCard> allCards = repo.findAll();

        Comparator<UnitCard> c = Comparator.comparing(UnitCard::getFaction).thenComparing(UnitCard::getSubFaction).thenComparing(UnitCard::getUnitName);
        Collections.sort(allCards, c);
        return allCards;
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



