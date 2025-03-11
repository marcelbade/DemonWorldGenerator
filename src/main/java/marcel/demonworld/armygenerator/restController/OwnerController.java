package marcel.demonworld.armygenerator.restController;


import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/owner")
public class OwnerController {

    @Autowired
    private UserService userService;

    @PostMapping("/changeToAdmin")
    public ResponseEntity<String> changeToAdmin(@RequestParam String userName) {

        userService.upgradeUserToAdmin(userName);
        return ResponseEntity.ok(userName);
    }


    @PostMapping("/changeToUser")
    public ResponseEntity<String> changeToUser(@RequestParam String userName) {

        userService.downgradeAdminToUser(userName);
        return ResponseEntity.ok(userName);
    }


    @PostMapping("/createNewOwner")
    public ResponseEntity<String> createNewOwner(@RequestParam String userName) {

        userService.upgradeUserToOwner(userName);
        return ResponseEntity.ok(userName);
    }

}
