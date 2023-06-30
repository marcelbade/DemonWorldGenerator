package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
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

    /**
     * Returns ALL unit cards currently in the game.
     * @return all unit cards in the game
     */
    @GetMapping("/factions")
    public List<UnitCard> getAllUnitCards() {
        return armyService.returnAll();
    }
}