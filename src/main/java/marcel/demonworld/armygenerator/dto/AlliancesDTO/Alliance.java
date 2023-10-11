package marcel.demonworld.armygenerator.dto.AlliancesDTO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "allies")
public class Alliance {

    @Id
    private Integer id;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "ally", columnDefinition = "text")
    private String ally;


}
