package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.game.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.game.UnitCardToFactionDataMapperInterface;
import marcel.demonworld.armygenerator.services.game.AllyAndAlternativesService;
import marcel.demonworld.armygenerator.services.game.FactionService;
import marcel.demonworld.armygenerator.services.game.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ArmyController {

    @Autowired
    SelectArmyService armyService;

    @Autowired
    AllyAndAlternativesService allyService;

    @Autowired
    FactionService factionService;

    @Autowired
    UnitCardToFactionDataMapperInterface unitCardToFactionDataMapperInterface;

    /**
     * Returns ALL unit cards currently in the game, as an unordered, unfiltered list.
     *
     * @return all unit cards in the game
     */
    @CrossOrigin
    @GetMapping("/factions")
    public List<UnitCard> getAllUnitCards() {
        return armyService.returnAll();
    }

    /**
     * Returns all unitCards in the game as a list of factionDTOs.
     * Every DTO contains the name of the faction, a list of its units and a list of its sub factions.
     *
     * @return all factions as a list of factionDTO.
     */
    @CrossOrigin
    @GetMapping("/factionDTOs")
    public List<FactionDataDTO> getAllFactionDTOs() {
        List<UnitCard> unitCards = armyService.returnAll();

        List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOS = allyService.returnAll();

        return unitCardToFactionDataMapperInterface.unitCardToFactionData(unitCards, allAllianceAndAlternativeDTOS);
    }

    /**
     * Returns all faction names in the game. The names are returned as a simple String array.
     *
     * @return all faction names as a String array.
     */
    @CrossOrigin
    @GetMapping("/factionNames")
    public List<String> getAllFactionNames() {
        return factionService.returnAll().stream().map(FactionDTO::getFactionName).collect(Collectors.toList());
    }
}