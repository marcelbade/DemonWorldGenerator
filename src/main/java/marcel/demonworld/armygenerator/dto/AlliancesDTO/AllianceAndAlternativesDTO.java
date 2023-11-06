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
@Table(name = "alliesandalternativelists")
public class AllianceAndAlternativesDTO {

    @Id
    private Integer id;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "ally", columnDefinition = "text")
    private String ally;

    @Column(name = "hasAlternativeLists", columnDefinition = "tinyint")
    private Boolean hasAlternativeLists;

    @Column(name = "numberOfChoices", columnDefinition = "tinyint")
    private Integer numberOfChoices;


}
