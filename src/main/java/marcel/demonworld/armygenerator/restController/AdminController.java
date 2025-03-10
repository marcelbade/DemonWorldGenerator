package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // TODO unfinished
//    @Autowired
//    private UnitService


    // TODO unfinished
    @DeleteMapping("/removeUser")
    public ResponseEntity<UserDTO> removeUser() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/createNewUnit")
    public ResponseEntity<UserDTO> createNewUnit() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/createNewItem")
    public ResponseEntity<UserDTO> createNewItem(@RequestBody EventDTO eventDTO) {

        return null;
    }


}
