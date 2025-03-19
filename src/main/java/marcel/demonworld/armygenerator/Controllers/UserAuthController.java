package marcel.demonworld.armygenerator.Controllers;


import jakarta.validation.Valid;
import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.auth.UserService;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private UnitCardService unitCardService;

    @Autowired
    private ItemCardService itemCardService;


    @PostMapping("/auth/user/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<String>("Logout Successfully!", HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody CredentialsDTO dto) {

        try {
            userService.updatePassword(dto);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok("password changed");
    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam String userName) {

        try {
            userService.deleteUser(userName);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok(userName);
    }


    @PostMapping("/createCustomUnit")
    public ResponseEntity<String> createCustomUnit(@RequestBody @Valid UnitCardDTO newCustomUnit) {

        UnitCard newUnit = null;

        try {
              newUnit = unitCardService.createNewUnit(newCustomUnit);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok(" custom unit created:" + newUnit.toString());
    }


    @PostMapping("/createCustomItem")
    public ResponseEntity<String> createCustomItem(@RequestBody @Valid ItemCardDTO newCustomItem) {
        try {
            itemCardService.createNewItem(newCustomItem);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok("custom item created");
    }

    @DeleteMapping("/deleteCustomItem")
    public ResponseEntity<String> deleteCustomItem(@RequestParam String customItemName, @RequestParam String faction) {
        try {
            itemCardService.deleteItem(customItemName, faction);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }
        return ResponseEntity.ok(customItemName + " deleted!");
    }

    @DeleteMapping("/deleteCustomUnit")
    public ResponseEntity<String> deleteCustomUnit(@RequestParam String customUnitName, @RequestParam String faction) {
        try {
            unitCardService.deleteUnit(customUnitName, faction);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }
        return ResponseEntity.ok(customUnitName + " deleted!");
    }
}
















