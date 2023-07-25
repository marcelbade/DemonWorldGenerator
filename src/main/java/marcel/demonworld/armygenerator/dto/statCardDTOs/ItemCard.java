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
    private String generic;
    @Column(name = "hasShield", columnDefinition = "text")
    private String hasShield;
    @Column(name = "mustBeMounted", columnDefinition = "text")
    private String mustBeMounted;
    @Column(name = "usableByCav", columnDefinition = "text")
    private String usableByCav;
    @Column(name = "maxRangeArmor", columnDefinition = "text")
    private String maxRangeArmor;
    @Column(name = "maxSize", columnDefinition = "text")
    private String maxSize;
    @Column(name = "subfaction", columnDefinition = "text")
    private String subfaction;
    @Column(name = "everyElement", columnDefinition = "text")
    private String everyElement;
    @Column(name = "limitedToUnit", columnDefinition = "text")
    private String limitedToUnit;
    @Column(name = "magicUsersOnly", columnDefinition = "text")
    private String magicUsersOnly;
    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;
    @Column(name = "itemType", columnDefinition = "text")
    private String itemType;
    @Column(name = "requiresWeaponType", columnDefinition = "text")
    private String requiresWeaponType;
    @Column(name = "banner", columnDefinition = "text")
    private Boolean requiresBanner;
    @Column(name = "musician", columnDefinition = "text")
    private Boolean requiresMusician;
    @Column(name = "points", columnDefinition = "text")
    private Integer pointCost;
    @Column(name = "altersMove", columnDefinition = "text")
    private Integer altersMove;
    @Column(name = "altersCharge", columnDefinition = "text")
    private Integer altersCharge;
    @Column(name = "altersSkirmish", columnDefinition = "text")
    private Integer altersSkirmish;
    @Column(name = "altersHold_maneuvers", columnDefinition = "text")
    private Integer altersHold_maneuvers;
    @Column(name = "controlZone_OverRun", columnDefinition = "text")
    private Integer controlZone_OverRun;
    @Column(name = "altersInitiative", columnDefinition = "text")
    private Integer altersInitiative;
    @Column(name = "altersSize", columnDefinition = "text")
    private Integer altersSize;
    @Column(name = "altersArmourRange", columnDefinition = "text")
    private Integer altersArmourRange;
    @Column(name = "altersArmourMelee", columnDefinition = "text")
    private Integer altersArmourMelee;
    @Column(name = "weapon1", columnDefinition = "text")
    private Integer weapon1;
    @Column(name = "weapon2", columnDefinition = "text")
    private Integer weapon2;
    @Column(name = "altersSkillMelee", columnDefinition = "text")
    private Integer altersSkillMelee;
    @Column(name = "rangedWeapon", columnDefinition = "text")
    private String rangedWeapon;
    @Column(name = "skillRange", columnDefinition = "text")
    private Integer skillRange;
    @Column(name = "altersFear", columnDefinition = "text")
    private Integer altersFear;
    @Column(name = "altersMoral1", columnDefinition = "text")
    private Integer altersMoral1;
    @Column(name = "altersMoral2", columnDefinition = "text")
    private Integer altersMoral2;
    @Column(name = "altersHitpoints", columnDefinition = "text")
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
