package marcel.demonworld.armygenerator.restController;


import jakarta.validation.Valid;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class UserAuthController {

    @Autowired
    private UserService userService;

    // TODO unfinished
//    @Autowired
//    private CustomUnitService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    // TODO unfinished
    @DeleteMapping("/delete")
    public ResponseEntity<UserDTO> deleteUser(@RequestBody CredentialsDTO credentialsDTO) {

        return null;
    }

    // TODO unfinished
    @PostMapping("/createCustomUnit")
    public ResponseEntity<UserDTO> createCustomUnit(@RequestBody @Valid UnitCardDTO newCustomUnit) {
        return null;
    }


    // TODO unfinished
    @DeleteMapping("/deleteCustomUnit")
    public ResponseEntity<UserDTO> deleteCustomUnit(@RequestBody @Valid String customUnitName) {
        return null;
    }


    // TODO unfinished
    @PostMapping("/createCustomItem")
    public ResponseEntity<UserDTO> createCustomItem(@RequestBody @Valid ItemCardDTO newCustomItem) {
        return null;
    }


    // TODO unfinished
    @DeleteMapping("/deleteCustomUnit")
    public ResponseEntity<UserDTO> deleteCustomItem(@RequestBody @Valid String customItemName) {
        return null;
    }

}
