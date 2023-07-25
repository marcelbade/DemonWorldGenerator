package marcel.demonworld.armygenerator.dto.statCardDTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * one dto for all army cards, no matter what the unit type is.
 * Equipped Items are store in a simple list<ItemCard>
 */


@Setter
@Getter
@Entity
@Table(name = "units")
public class UnitCard implements DemonWorldCard {

    @Id
    private Integer id;

    //mySQL doesn't use text, it uses "text". in order to avoid a code breaking SQL error columnDef. must be set!
    // TODO: you accidentally used text instead of int for one of the weapon values!
    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "unitName", columnDefinition = "text")
    private String unitName;

    @Column(name = "rangedWeapon", columnDefinition = "text")
    private String rangedWeapon;

    @Column(name = "rangedAttackStats", columnDefinition = "text")
    private String rangedAttackStats;

    @Column(name = "isUnique", columnDefinition = "text")
    private Boolean uniqueUnit;

    @Column(name = "isMounted", columnDefinition = "text")
    private Boolean isMounted;

    @Column(name = "leader", columnDefinition = "text")
    private Boolean leader;

    @Column(name = "standardBearer", columnDefinition = "text")
    private Boolean standardBearer;

    @Column(name = "musician", columnDefinition = "text")
    private Boolean musician;

    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;

    @Column(name = "wedgeFormation", columnDefinition = "text")
    private Boolean wedgeFormation;

    @Column(name = "skirmishFormation", columnDefinition = "text")
    private Boolean skirmishFormation;

    @Column(name = "squareFormation", columnDefinition = "text")
    private Boolean squareFormation;

    @Column(name = "horde", columnDefinition = "text")
    private Boolean horde;

    @Column(name = "commandStars")
    private Integer commandStars;

    @Column(name = "magic")
    private Integer magic;

    @Column(name = "subfaction", columnDefinition = "text")
    private String subFaction;

    @Column(name = "points")
    private Integer points;

    @Column(name = "elements")
    private Integer numberOfElements;

    @Column(name = "move")
    private Integer move;

    @Column(name = "charge")
    private Integer charge;

    @Column(name = "skirmish")
    private Integer skirmish;

    @Column(name = "hold_maneuvers")
    private Integer hold_maneuvers;

    @Column(name = "controlZone")
    private Integer controlZone;

    @Column(name = "OverRun")
    private Integer overRun;

    @Column(name = "Initiative")
    private Integer initiative;

    @Column(name = "size")
    private Integer unitSize;

    @Column(name = "armourRange")
    private Integer armourRange;

    @Column(name = "armourMelee")
    private Integer armourMelee;

    @Column(name = "weapon1Name")
    private Integer weapon1Name;

    @Column(name = "weapon1")
    private Integer weapon1;

    @Column(name = "weapon2Name")
    private Integer weapon2Name;

    @Column(name = "weapon2")
    private Integer weapon2;

    @Column(name = "weapon3Name")
    private Integer weapon3Name;

    @Column(name = "weapon3")
    private Integer weapon3;

    @Column(name = "skillMelee", columnDefinition = "text")
    private Integer skillMelee;

    @Column(name = "skillRange")
    private Integer skillRange;

    @Column(name = "fear")
    private Integer fear;

    @Column(name = "moral1")
    private Integer moral1;

    @Column(name = "moral2")
    private Integer moral2;

    @Column(name = "hitpoints")
    private Integer hitpoints;

    @Column(name = "specialRules", columnDefinition = "text")
    private String specialRules;

    @Column(name = "isHighFlyer", columnDefinition = "text")
    private Boolean isHighFlyer;

    @Column(name = "isLowFlyer", columnDefinition = "text")
    private Boolean isLowFlyer;

    @Column(name = "hasShield", columnDefinition = "text")
    private Boolean hasShield;

    @Column(name = "chargeBonus", columnDefinition = "text")
    private Boolean chargeBonus;


    @Override
    public String getName() {
        return this.unitName;
    }

    @Override
    public int getPoints() {
        return this.points;
    }


}
