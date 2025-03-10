package marcel.demonworld.armygenerator.mappingInterfaces.auth;


import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.auth.User;

public interface UserMapperInterface {


    // user entity -> UserDTO
    UserDTO entityToDTO(User user);

    // SignUpDTO -> UserDTO
    User signUpDtoToEntity(SignUpDTO dto);

    // SignUpDTO -> UserDTO
    UserDTO signUpDtoToUserDTO(SignUpDTO dto);

    //  UserDTO  -> User
    User dtoToEntity(UserDTO dto);


}
