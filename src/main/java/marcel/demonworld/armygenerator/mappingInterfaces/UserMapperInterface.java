package marcel.demonworld.armygenerator.mappingInterfaces;


import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.User;

public interface UserMapperInterface {


    // user entity -> UserDTO
    UserDTO entityToDTO(User user);

    // SignUpDTO -> UserDTO
    User signUpDtoToEntity(SignUpDTO dto);


    //  UserDTO  -> User
    User dtoToEntity(UserDTO dto);


}
