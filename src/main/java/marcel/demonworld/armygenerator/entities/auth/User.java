package marcel.demonworld.armygenerator.entities.auth;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "userName", columnDefinition = "text")
    private String userName;
    @Column(name = "userPassword", columnDefinition = "text")
    private String password;
    @Column(name = "isAdmin", columnDefinition = "text")
    private Boolean isAdmin;

}
