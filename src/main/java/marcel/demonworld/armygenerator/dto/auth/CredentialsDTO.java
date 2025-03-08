package marcel.demonworld.armygenerator.dto.auth;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDTO {

    private String userName;
    private char[] password;

}
