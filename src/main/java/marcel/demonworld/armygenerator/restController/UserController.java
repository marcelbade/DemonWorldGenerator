package marcel.demonworld.armygenerator.restController;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO) {

        UserDTO userdto = userService.loginUser(credentialsDTO);
        userdto.setToken(userAuthenticationProvider.createToken(userdto.getUserName()));

        return ResponseEntity.ok(userdto);

    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid SignUpDTO user) {
        UserDTO createdUser = userService.registerUser(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getUserName()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }




//    @PostMapping("/delete")
//    public ResponseEntity<UserDTO> register((@RequestBody CredentialsDTO credentialsDTO) {
//
//        UserDTO userdto = userService.logout(credentialsDTO);
//        userdto.setToken(userAuthenticationProvider.createToken(user.getLogin));
//
//        return ResponseEntity.ok(userdto);
//    }
}
