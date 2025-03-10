package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.game.ArmyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/armyLists")
public class ArmyListController {

    @Autowired
    private ArmyListService listService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @GetMapping("/getListsForUser")
    public List<ArmyListDTO> getAllListsForUser() {


        return null;
    }

    // TODO unfinished
    // add and update!
    @PostMapping("/addList")
    public ResponseEntity<ArmyListDTO> saveList(@RequestBody ArmyListDTO listDTO) {

        return null;
    }

    // TODO unfinished
    @DeleteMapping("/deleteList")
    public ResponseEntity<ArmyListDTO> deleteList(@RequestBody ArmyListDTO listDTO) {

        return null;
    }


}
