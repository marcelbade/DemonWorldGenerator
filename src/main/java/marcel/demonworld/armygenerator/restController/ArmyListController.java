package marcel.demonworld.armygenerator.restController;


import lombok.RequiredArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.game.ArmyListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/armyLists")
@RequiredArgsConstructor
public class ArmyListController {

    private final ArmyListService listService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @GetMapping("/getArmyLists")
    public List<ArmyListDTO> getAllListsForUser() {


        return null;
    }

    @PostMapping("/saveArmyList")
    public ResponseEntity<ArmyListDTO> saveList(@RequestBody ArmyListDTO listDTO) {

        return null;
    }

    @DeleteMapping("/deleteArmyList")
    public ResponseEntity<ArmyListDTO> deleteList(@RequestBody ArmyListDTO listDTO) {

        return null;
    }


}
