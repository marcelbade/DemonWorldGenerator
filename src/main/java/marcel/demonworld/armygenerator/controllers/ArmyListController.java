package marcel.demonworld.armygenerator.controllers;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.security.MethodLevelSecurityConfig;
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

    
    @GetMapping("/getListsForUser")
    public List<ArmyListDTO> getAllListsForUser(@RequestParam String userName) {

        MethodLevelSecurityConfig.authenticateUser(userName);

        return listService.returnListsForUser(userName);
    }

    // add and update!
    @PostMapping("/storeList")
    public ResponseEntity<String> saveList(@RequestBody ArmyListDTO listDTO) {

        MethodLevelSecurityConfig.authenticateUser(listDTO.getUserName());

        listService.saveOrUpdateArmyList(listDTO);
        return ResponseEntity.ok(listDTO.getListName());
    }

    @DeleteMapping("/deleteList")
    public ResponseEntity<String> deleteList( @RequestParam String userName, @RequestParam Long listId) {

        MethodLevelSecurityConfig.authenticateUser(userName);


        listService.deleteList(listId);

        return ResponseEntity.ok(listId.toString());
    }

}
