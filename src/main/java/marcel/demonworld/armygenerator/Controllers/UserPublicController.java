package marcel.demonworld.armygenerator.Controllers;


import jakarta.validation.Valid;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.security.UserAuthenticationProvider;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/public/user")
public class UserPublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

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
}
