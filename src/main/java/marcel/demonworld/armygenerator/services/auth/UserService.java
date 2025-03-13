package marcel.demonworld.armygenerator.services.auth;


import lombok.RequiredArgsConstructor;
import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.auth.User;
import marcel.demonworld.armygenerator.mapperImplementations.auth.UserMapper;
import marcel.demonworld.armygenerator.repositories.auth.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    /**
     * Method attempts to find user in the DB and checks the supplied pw.
     *
     * @param credentialsDTO login Data supplied by user.
     * @return a user DTO if the user is found and pw is correct.
     */
    public UserDTO loginUser(CredentialsDTO credentialsDTO) {
        User user = repo.findByUserName(credentialsDTO.getUserName())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), user.getPassword())) {
            return userMapper.entityToDTO(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    /**
     * Method attempts to register user by first checking if they already exist, then
     * saving the user in the DB if the answer is false.
     *
     * @param signUpDTO login data supplied by user.
     * @return UserDTO of the newly registered user.
     */
    public UserDTO registerUser(SignUpDTO signUpDTO) {
        Optional<User> optionalUser = repo.findByUserName(signUpDTO.getUserName());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpDtoToEntity(signUpDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.getPassword())));

        User savedUser = repo.save(user);

        return userMapper.entityToDTO(savedUser);
    }


    public void updatePassword(CredentialsDTO credentials) {

        Optional<User> optionalUser = repo.findByUserName(credentials.getUserName());
        if (!optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        String encodedPW = passwordEncoder.encode(CharBuffer.wrap(credentials.getPassword()));

        if (encodedPW.equals(optionalUser.get().getPassword())) {
            throw new AppException("new password must differ from current password!", HttpStatus.FOUND);
        }

        optionalUser.get().setPassword(encodedPW);
        repo.save(optionalUser.get());
    }


    public void deleteUser(String userName) {

        User user = repo
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsDeleted(true);
        repo.save(user);
    }

    // turn another user to admin
    public void upgradeUserToAdmin(String userName) {
        User user = repo
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(true);
        repo.save(user);

    }

    // turn another admin to user
    public void downgradeAdminToUser(String userName) {
        User user = repo
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(false);
        repo.save(user);

    }


    public UserDTO findByUsername(String username) {
        User user = repo.findByUserName(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.entityToDTO(user);
    }

    // turn another user to admin
    public void upgradeUserToOwner(String userName) {
        User user = repo
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(false);
        user.setIsOwner(true);

        repo.save(user);

    }

}