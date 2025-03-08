package marcel.demonworld.armygenerator.mappingInterfaces.auth;


import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.auth.User;

public interface UserMapperInterface {


    // user entity -> UserDTO
    UserDTO userToUserDto(User user);

    // SignUpDTO -> UserDTO
    User signUpDtoToUser(SignUpDTO dto);

    // SignUpDTO -> UserDTO
    UserDTO signUpDtoToUserDTO(SignUpDTO dto);

    //  UserDTO  -> User
    User userDtoToUser(UserDTO dto);


}
