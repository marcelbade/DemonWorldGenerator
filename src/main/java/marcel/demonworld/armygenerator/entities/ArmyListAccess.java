package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "listSharedWith")
public class ArmyListAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sharedWithUser", columnDefinition = "text")
    private String sharedWithUser;

    @ManyToOne()
    @JoinColumn(name = "listId")
    private ArmyList sharedList;

}
