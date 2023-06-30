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
    @Column(name = "forUnit", columnDefinition = "text")
    private Boolean forUnit;
    @Column(name = "name", columnDefinition = "text")
    private String itemName;
    @Column(name = "generic", columnDefinition = "text")
    private Boolean generic;
    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;
    @Column(name = "itemType", columnDefinition = "text")
    private String itemType;
    @Column(name = "requirement", columnDefinition = "text")
    private String requirement;
    @Column(name = "banner", columnDefinition = "text")
    private Boolean banner;
    @Column(name = "musician", columnDefinition = "text")
    private Boolean musician;
    @Column(name = "points", columnDefinition = "text")
    private Integer pointCost;
    @Column(name = "move", columnDefinition = "text")
    private Integer move;
    @Column(name = "charge", columnDefinition = "text")
    private Integer charge;
    @Column(name = "skirmish", columnDefinition = "text")
    private Integer skirmish;
    @Column(name = "hold_maneuvers", columnDefinition = "text")
    private Integer hold_maneuvers;
    @Column(name = "controlZone_OverRun", columnDefinition = "text")
    private Integer controlZone_OverRun;
    @Column(name = "Initiative", columnDefinition = "text")
    private Integer Initiative;
    @Column(name = "size", columnDefinition = "text")
    private Integer size;
    @Column(name = "armourRange", columnDefinition = "text")
    private Integer armourRange;
    @Column(name = "armourMelee", columnDefinition = "text")
    private Integer armourMelee;
    @Column(name = "weapon1", columnDefinition = "text")
    private Integer weapon1;
    @Column(name = "weapon2", columnDefinition = "text")
    private Integer weapon2;
    @Column(name = "skillMelee", columnDefinition = "text")
    private Integer skillMelee;
    @Column(name = "rangedWeapon", columnDefinition = "text")
    private String rangedWeapon;
    @Column(name = "skillRange", columnDefinition = "text")
    private Integer skillRange;
    @Column(name = "fear", columnDefinition = "text")
    private Integer fear;
    @Column(name = "moral1", columnDefinition = "text")
    private Integer moral1;
    @Column(name = "moral2", columnDefinition = "text")
    private Integer moral2;
    @Column(name = "hitpoints", columnDefinition = "text")
    private Integer hitpoints;
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
