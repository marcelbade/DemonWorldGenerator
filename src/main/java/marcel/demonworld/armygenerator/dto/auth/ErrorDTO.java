package marcel.demonworld.armygenerator.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorDTO {

    private String message;
}
