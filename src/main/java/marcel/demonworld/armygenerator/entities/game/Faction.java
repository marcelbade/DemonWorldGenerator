package marcel.demonworld.armygenerator.entities.game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "factions")
public class Faction {

    @Id
    @Column(name = "id", columnDefinition = "text")
    private Integer id;

    @Column(name = "factionName", columnDefinition = "text")
    private String factionName;
}
