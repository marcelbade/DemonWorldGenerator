package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.auth.SignUpDTO;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.entities.User;
import marcel.demonworld.armygenerator.mappingInterfaces.UserMapperInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Primary
public class UserMapper implements UserMapperInterface {
    @Override
    public UserDTO entityToDTO(User user) {

        return UserDTO.builder().id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .emailAddress(user.getEmailAddress())
                .isAdmin(user.getIsAdmin())
                .isOwner(user.getIsOwner())
                .isDeleted(user.getIsDeleted())
                .displayDeleteConfirmation(user.getDisplayDeleteConfirmation())
                .displayOverrideConfirmation(user.getDisplayOverrideConfirmation())
                .build();
    }


    @Override
    public User signUpDtoToEntity(SignUpDTO dto) {
        return User.builder()
                .id(null)
                .userName(dto.getUserName())
                .password(Arrays.toString(dto.getPassword()))
                .emailAddress(dto.getEmailAddress())
                .isAdmin(false)
                .isOwner(false)
                .isDeleted(false)
                .displayDeleteConfirmation(true)
                .displayOverrideConfirmation(true)
                .build();
    }


    @Override
    public User dtoToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .emailAddress(dto.getEmailAddress())
                .isAdmin(dto.getIsAdmin())
                .isOwner(dto.getIsOwner())
                .isDeleted(dto.getIsDeleted())
                .displayDeleteConfirmation(dto.getDisplayDeleteConfirmation())
                .displayOverrideConfirmation(dto.getDisplayOverrideConfirmation())
                .build();
    }
}
