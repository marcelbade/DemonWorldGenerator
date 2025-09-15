package marcel.demonworld.armygenerator.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "second_subfactions")
public class SecondSubFaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factionId", columnDefinition = "text")
    private Faction faction;

    @Column(name = "secondSubFaction", columnDefinition = "text")
    private String secondSubFaction;

}
