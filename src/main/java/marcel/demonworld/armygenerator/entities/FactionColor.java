package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "faction_colors")
public class FactionColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factionId", columnDefinition = "text")
    private Faction faction;

    @Column(name = "rgb_a", columnDefinition = "integer")
    private Integer rgbA;

    @Column(name = "rgb_b", columnDefinition = "integer")
    private  Integer rgbB;

    @Column(name = "rgb_c", columnDefinition = "integer")
    private Integer rgbC;

}
