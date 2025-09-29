package marcel.demonworld.armygenerator.controllers;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.services.game.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/spells")
public class EditSpellController {

    @Autowired
    private SpellService service;


    // add and update!
    @PostMapping("/editSpell")
    public ResponseEntity<String> editSpellProperties(@RequestBody SpellDTO spellDTO) {


        // TODO: see kanban note: Add Magic to the UI
       // MethodLevelSecurityConfig.authenticateUser(spellDTO.getUserName());

        service.updateSpell(spellDTO);
        return ResponseEntity.ok(spellDTO.getSpellName());
    }

}
