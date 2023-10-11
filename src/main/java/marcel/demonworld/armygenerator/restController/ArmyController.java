package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.AlliancesDTO.Alliance;
import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionData;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.mappingInterfaces.UnitCardToFactionDataMapperInterface;
import marcel.demonworld.armygenerator.services.AllyService;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ArmyController {

    @Autowired
    SelectArmyService armyService;

    @Autowired
    AllyService allyService;

    @Autowired
    UnitCardToFactionDataMapperInterface unitCardToFactionDataMapperInterface;

    /**
     * Returns ALL unit cards currently in the game, as an unordered, unfiltered list.
     *
     * @return all unit cards in the game
     */
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
    @GetMapping("/factionDTOs")
    public List<FactionData> getAllFactionDTOs() {
        List<UnitCard> unitCards = armyService.returnAll();
        List<Alliance> allAlliances = allyService.returnAll();

        return unitCardToFactionDataMapperInterface.unitCardToFactionData(unitCards, allAlliances);
    }
}