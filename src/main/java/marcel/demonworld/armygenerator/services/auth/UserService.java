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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    /**
     * Method attempts to find user in the DB and checks the supplied pw.
     *
     * @param credentialsDTO login Data supplied by user.
     * @return a user DTO if the user is found and pw is correct.
     */
    public UserDTO loginUser(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByUserName(credentialsDTO.getUserName())
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
        Optional<User> optionalUser = userRepository.findByUserName(signUpDTO.getUserName());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpDtoToEntity(signUpDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.entityToDTO(savedUser);
    }


    public void deleteUser(UserDTO userDTO) {

        userRepository.delete(userMapper.dtoToEntity(userDTO));
    }

    // turn another user to admin
    public UserDTO upgradeUserToAdmin(String userName) {
        User user = userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(true);
        userRepository.save(user);

        return userMapper.entityToDTO(user);
    }

    // turn another admin to user
    public UserDTO downgradeAdminToUser(String userName) {
        User user = userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(false);
        userRepository.save(user);

        return userMapper.entityToDTO(user);
    }


    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.entityToDTO(user);
    }

    // turn another user to admin
    public UserDTO upgradeUserToOwner(String userName) {
        User user = userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(false);
        user.setIsOwner(true);

        userRepository.save(user);

        return userMapper.entityToDTO(user);
    }

}