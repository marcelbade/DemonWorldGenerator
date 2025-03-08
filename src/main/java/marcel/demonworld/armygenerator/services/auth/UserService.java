package marcel.demonworld.armygenerator.services.auth;


import lombok.RequiredArgsConstructor;

import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.auth.CredentialsDTO;
import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.auth.User;
import marcel.demonworld.armygenerator.mappingInterfaces.auth.UserMapperInterface;
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

    private final UserMapperInterface userMapper;

    public UserDTO loginUser(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByUserName(credentialsDTO.getUserName())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), user.getPassword())) {
            return userMapper.userToUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO registerUser(SignUpDTO userDto) {
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.userToUserDto(savedUser);
    }


    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(userMapper.userDtoToUser(userDTO));
    }

    public UserDTO upgradeUserToAdmin(UserDTO userDTO) {
        User user = userRepository
                .findByUserName(userDTO.getUserName())
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(true);
        userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    public UserDTO downgradeAdminToUser(UserDTO userDTO) {
        User user = userRepository
                .findByUserName(userDTO.getUserName())
                .orElseThrow(() -> new AppException("user not found", HttpStatus.NOT_FOUND));

        user.setIsAdmin(false);
        userRepository.save(user);

        return userMapper.userToUserDto(user);
    }


    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.userToUserDto(user);
    }

}