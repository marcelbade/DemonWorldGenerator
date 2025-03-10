package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.services.auth.UserService;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UnitCardService unitCardService;

    @Autowired
    private ItemCardService itemCardService;


    // TODO unfinished
    @DeleteMapping("/removeUser")
    public ResponseEntity<UserDTO> removeUser() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/createNewUnit")
    public ResponseEntity createNewUnit(@RequestBody UnitCardDTO newUnit) {
        unitCardService.createNewUnit(newUnit);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    // TODO unfinished
    @PostMapping("/createNewItem")
    public ResponseEntity createNewItem(@RequestBody ItemCardDTO newItem) {
        itemCardService.createNewItem(newItem);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
