package marcel.demonworld.armygenerator.Controllers;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.FactionDataDTO;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapper;
import marcel.demonworld.armygenerator.services.game.AllyAndAlternativesService;
import marcel.demonworld.armygenerator.services.game.FactionService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/public/game")
public class ArmyController {

    @Autowired
    private UnitCardService armyService;

    @Autowired
    private AllyAndAlternativesService allyService;

    @Autowired
    private FactionService factionService;

    @Autowired
    private UnitCardToFactionDataMapper unitCardToFactionDataMapper;

    /**
     * Returns ALL unit cards currently in the game, as an unordered, unfiltered list.
     *
     * @return all unit cards in the game
     */
    @GetMapping("/allUnits")
    public List<UnitCardDTO> getAllUnitCards() {
        return armyService.returnAll();
    }

    /**
     * Returns all unitCards in the game as a list of factionDTOs.
     * Every DTO contains the name of the faction, a list of its units and a list of its sub factions.
     *
     * @return all factions as a list of factionDTO.
     */
    @GetMapping("/factionDTOs")
    public List<FactionDataDTO> getAllFactionDTOs() {
        List<UnitCardDTO> unitCards = armyService.returnAll();

        List<AllianceAndAlternativesDTO> allAllianceAndAlternativeDTOS = allyService.returnAll();

        return unitCardToFactionDataMapper.unitCardToFactionData(unitCards, allAllianceAndAlternativeDTOS);
    }

    /**
     * Returns all faction names in the game. The names are returned as a simple String array.
     *
     * @return all faction names as a String array.
     */
    @GetMapping("/factionNames")
    public List<String> getAllFactionNames() {
        return factionService.returnAll().stream().map(FactionDTO::getFactionName).collect(Collectors.toList());
    }
}