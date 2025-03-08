package marcel.demonworld.armygenerator.mapperImplementations.auth;

import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.auth.User;
import marcel.demonworld.armygenerator.mappingInterfaces.auth.UserMapperInterface;

import java.util.Arrays;

public class UserMapper implements UserMapperInterface {
    @Override
    public UserDTO userToUserDto(User user) {

        return UserDTO.builder().id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    @Override
    public User signUpDtoToUser(SignUpDTO dto) {
        return User.builder()
                .userName(dto.getUserName())
                .password(Arrays.toString(dto.getPassword()))
                // TODO you need to put more logic into admin stuff!
                .isAdmin(false)
                .build();
    }
            // TODO make sure that this is ok!
    @Override
    public UserDTO signUpDtoToUserDTO(SignUpDTO dto) {
        return UserDTO.builder()
                .userName(dto.getUserName())
                .password(Arrays.toString(dto.getPassword()))
                .build();
    }

    @Override
    public User userDtoToUser(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .isAdmin(dto.getIsAdmin())
                .build();
    }
}
