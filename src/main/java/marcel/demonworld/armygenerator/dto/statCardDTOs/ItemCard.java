package marcel.demonworld.armygenerator.dto.statCardDTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Domain Model/DTO for item cards. NOTE THE TABLE NAME :).
 */
@Setter
@Getter
@Entity
@Table(name = "items")
public class ItemCard implements DemonWorldCard {

    @Id
    private int id;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "name", columnDefinition = "text")
    private String itemName;

    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;

    @Column(name = "requirement", columnDefinition = "text")
    private String requirement;

    @Column(name = "banner", columnDefinition = "text")
    private boolean banner;

    @Column(name = "musician", columnDefinition = "text")
    private boolean musician;

    @Column(name = "points")
    private int points;

    @Column(name = "move")
    private Integer move;

    @Column(name = "charge" )
    private Integer charge;

    @Column(name = "skirmish" )
    private Integer skirmish;

    @Column(name = "hold_maneuvers")
    private Integer hold_maneuvers;

    @Column(name = "controlZone_OverRun" )
    private Integer controlZone_OverRun;

    @Column(name = "Initiative", columnDefinition = "text")
    private Integer Initiative;

    @Column(name = "size", columnDefinition = "text")
    private Integer size;

    @Column(name = "armourRange" )
    private Integer armourRange;

    @Column(name = "armourMelee" )
    private Integer armourMelee;

    @Column(name = "weapon1" )
    private Integer weapon1;

    @Column(name = "weapon2" )
    private Integer weapon2;

    @Column(name = "skillMelee")
    private Integer skillMelee;

    @Column(name = "rangedWeapon" , columnDefinition = "text")
    private String rangedWeapon;

    @Column(name = "skillRange" )
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


    @Column(name = "type", columnDefinition = "text")
    private String type;

    @Column(name = "generic", columnDefinition = "text")
    private Boolean generic;


    // override the Lombok getter.
    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public String getName() {
        return this.itemName;
    }

    /**
     * getter for  the attribute unitType.
     *
     * @return the type of unit that can use this item:
     * <ul>
     *     <li>U = unit</li>
     *     <li>H = heroes, characters, mages...</li>
     *     <li>M = mages</li>
     *     <li>* = everyone</li>
     * </ul>
     */
    @Override
    public String getSubFaction() {
        return getUnitType();
    }

}
