package marcel.demonworld.armygenerator.dto.FactionsDTO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "factions")
public class FactionDTO {

    @Id
    @Column(name = "id", columnDefinition = "text")
    private Integer id;

    @Column(name = "factionName", columnDefinition = "text")
    private String factionName;
}
