package marcel.demonworld.armygenerator.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private Boolean isAdmin;
    private String token;

}
