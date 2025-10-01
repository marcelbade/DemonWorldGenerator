package marcel.demonworld.armygenerator.controllers;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.services.game.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/spells")
public class EditSpellController {

    @Autowired
    private SpellService service;



    // add and update!
    @PostMapping("/editSpell")
    public ResponseEntity<List<SpellDTO>> editSpellProperties(@RequestBody SpellDTO spellDTO) {

        service.updateSpell(spellDTO);
        List<SpellDTO> allSpells = service.getAllSpells();
        return ResponseEntity.ok(allSpells);
    }

}
