package marcel.demonworld.armygenerator.entities.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.JSONConverter.JSONObjectConverter;
import org.json.simple.JSONObject;

@Setter
@Getter
@Entity
@Table(name = "alliesandalternativelists")
public class AllianceAndAlternatives {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "ally", columnDefinition = "text")
    private String ally;

    @Column(name = "hasAlternativeLists", columnDefinition = "tinyint")
    private Boolean hasAlternativeLists;

    @Column(name = "numberOfChoices", columnDefinition = "tinyint")
    private Integer numberOfChoices;

    @Lob
    @Column(name = "alternativeSubFaction", columnDefinition = "JSON")
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject alternativeSubFactions;

}
