package marcel.demonworld.armygenerator.controllers;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.SpellFactionDTO;
import marcel.demonworld.armygenerator.services.game.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/game")
public class SpellController {

    @Autowired
    SpellService service;

    @GetMapping("/allSpells")
    public List<SpellDTO> getAllSpells() {
        return service.getAllSpells();
    }

    @GetMapping("/spellsOrderedByFaction")
    public List<SpellFactionDTO> getAllSpellsOrderedByFaction() {
        return service.getAllSpellsOrderedByFaction();
    }

}
