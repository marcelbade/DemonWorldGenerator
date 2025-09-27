package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Spells")
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "spellName", columnDefinition = "text")
    private String spellName;

    @Column(name = "effect", columnDefinition = "text")
    private String effect;

    @Column(name = "duration", columnDefinition = "text")
    private String duration;

    @Column(name = "requirements", columnDefinition = "text")
    private String requirements;

    @Column(name = "target", columnDefinition = "text")
    private String target;

    @Column(name = "spellTier", columnDefinition = "text")
    private String spellTier;

}
