package marcel.demonworld.armygenerator.dto.statCardDTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "items")
public class ItemCard implements DemonWorldCard {


    @Id
    @Column(name = "id", columnDefinition = "text")
    private Integer id;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "name", columnDefinition = "text")
    private String itemName;

    @Column(name = "generic", columnDefinition = "text")
    private Boolean generic;

    @Column(name = "hasShield", columnDefinition = "text")
    private Boolean hasShield;

    @Column(name = "mustBeMounted", columnDefinition = "text")
    private Boolean mustBeMounted;

    @Column(name = "usableByCav", columnDefinition = "text")
    private Boolean usableByCav;

    @Column(name = "maxRangeArmor")
    private Integer maxRangeArmor;

    @Column(name = "maxSize")
    private Integer maxSize;

    @Column(name = "subfaction", columnDefinition = "text")
    private String subfaction;

    @Column(name = "everyElement", columnDefinition = "text")
    private Boolean everyElement;

    @Column(name = "limitedToUnit", columnDefinition = "text")
    private Boolean limitedToUnit;

    @Column(name = "magicUsersOnly", columnDefinition = "text")
    private Boolean magicUsersOnly;

    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;

    @Column(name = "itemType", columnDefinition = "text")
    private String itemType;

    @Column(name = "requiresWeaponType", columnDefinition = "text")
    private String requiresWeaponType;

    @Column(name = "requiresBanner", columnDefinition = "text")
    private Boolean requiresBanner;

    @Column(name = "requiresMusician", columnDefinition = "text")
    private Boolean requiresMusician;

    @Column(name = "points", columnDefinition = "text")
    private Integer pointCost;

    @Column(name = "altersMove")
    private Integer altersMove;

    @Column(name = "altersCharge")
    private Integer altersCharge;

    @Column(name = "altersSkirmish")
    private Integer altersSkirmish;

    @Column(name = "altersHold_maneuvers")
    private Integer altersHold_maneuvers;

    @Column(name = "controlZone_OverRun")
    private Integer controlZone_OverRun;

    @Column(name = "altersInitiative")
    private Integer altersInitiative;

    @Column(name = "altersSize")
    private Integer altersSize;

    @Column(name = "altersArmourRange")
    private Integer altersArmourRange;

    @Column(name = "altersArmourMelee")
    private Integer altersArmourMelee;

    @Column(name = "altersWeapon1")
    private Integer altersWeapon1;

    @Column(name = "altersWeapon2")
    private Integer altersWeapon2;

    @Column(name = "altersSkillMelee")
    private Integer altersSkillMelee;

    @Column(name = "rangedWeapon", columnDefinition = "text")
    private String rangedWeapon;

    @Column(name = "altersSkillRange")
    private Integer altersSkillRange;

    @Column(name = "altersFear")
    private Integer altersFear;

    @Column(name = "altersMoral1")
    private Integer altersMoral1;

    @Column(name = "altersMoral2")
    private Integer altersMoral2;

    @Column(name = "altersHitpoints")
    private Integer altersHitpoints;

    @Column(name = "specialRules", columnDefinition = "text")
    private String specialRules;


    @Override
    public int getPoints() {
        return this.pointCost;
    }

    @Override
    public String getName() {
        return this.itemName;
    }

    @Override
    public String getSubFaction() {
        return null;
    }


}
