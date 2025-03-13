package marcel.demonworld.armygenerator.Controllers;


import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.services.auth.UserService;
import marcel.demonworld.armygenerator.services.game.ItemCardService;
import marcel.demonworld.armygenerator.services.game.UnitCardService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/createNewUnit")
    public ResponseEntity<String> createNewUnit(@RequestBody UnitCardDTO newUnit) {

        try {
            unitCardService.createNewUnit(newUnit);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok("unit created");
    }

    public ResponseEntity<String> updateUnit(@RequestBody UnitCardDTO unit) {

        unitCardService.updateUnitCard(unit);

        return ResponseEntity.ok("unit updated");
    }


    @PostMapping("/createNewItem")
    public ResponseEntity<String> createNewItem(@RequestBody ItemCardDTO newItem) {

        try {
            itemCardService.createNewItem(newItem);
        } catch (AppException appException) {
            return ResponseEntity
                    .status(appException.getStatus())
                    .body(appException.getMessage());
        }

        return ResponseEntity.ok("unit created");
    }

    public ResponseEntity<String> updateItem(@RequestBody ItemCardDTO item) {

        itemCardService.updateItem(item);

        return ResponseEntity.ok("unit updated");
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

}
