package marcel.demonworld.armygenerator.Controllers;


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
    public List<ArmyListDTO> getAllListsForUser(@RequestParam String userName) {
        return listService.returnListsForUser(userName);
    }

    // add and update!
    @PostMapping("/addList")
    public ResponseEntity<String> saveList(@RequestBody ArmyListDTO listDTO) {

        listService.addArmyList(listDTO);

        return ResponseEntity.ok(listDTO.getListName());
    }

    @DeleteMapping("/deleteList")
    public ResponseEntity<String> deleteList(@RequestParam String listName) {

        ArmyListDTO listByName = listService.findListByName(listName);
        listService.deleteList(listByName);

        return ResponseEntity.ok(listName);
    }

}
