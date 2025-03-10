package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/owner")
public class OwnerController {

    @Autowired
    private UserService userService;


    // TODO unfinished
    @PostMapping("/changeToAdmin")
    public ResponseEntity<UserDTO> changeToAdmin() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/changeToUser")
    public ResponseEntity<UserDTO> changeToUser() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/createNewOwner")
    public ResponseEntity<UserDTO> createNewOwner(@RequestBody EventDTO eventDTO) {

        return null;
    }


}
