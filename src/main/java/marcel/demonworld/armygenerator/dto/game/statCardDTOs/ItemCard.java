package marcel.demonworld.armygenerator.dto.game.statCardDTOs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// import javax.persistence.*;


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

    @Column(name = "itemName", columnDefinition = "text")
    private String itemName;

    @Column(name = "isGeneric", columnDefinition = "integer")
    private Boolean isGeneric;

    @Column(name = "requiresShield", columnDefinition = "integer")
    private Boolean requiresShield;

    @Column(name = "mustBeMounted", columnDefinition = "integer")
    private Boolean mustBeMounted;

    @Column(name = "usableByCav", columnDefinition = "integer")
    private Boolean usableByCav;

    @Column(name = "maxRangeArmor")
    private Integer maxRangeArmor;

    @Column(name = "maxSize")
    private Integer maxSize;

    @Column(name = "subfaction", columnDefinition = "text")
    private String subfaction;

    @Column(name = "everyElement", columnDefinition = "integer")
    private Boolean everyElement;

    @Column(name = "limitedToUnit", columnDefinition = "text")
    private String limitedToUnit;

    @Column(name = "magicUsersOnly", columnDefinition = "integer")
    private Boolean magicUsersOnly;

    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;

    @Column(name = "itemType", columnDefinition = "text")
    private String itemType;

    @Column(name = "requiresWeaponType", columnDefinition = "text")
    private String requiresWeaponType;

    @Column(name = "requiresBanner", columnDefinition = "integer")
    private Boolean requiresBanner;

    @Column(name = "requiresMusician", columnDefinition = "integer")
    private Boolean requiresMusician;

    @Column(name = "points", columnDefinition = "text")
    private Integer pointCost;

    @Column(name = "move")
    private Integer move;

    @Column(name = "charge")
    private Integer charge;

    @Column(name = "skirmish")
    private Integer skirmish;

    @Column(name = "hold_maneuvers")
    private Integer hold_maneuvers;

    @Column(name = "controlZone_OverRun")
    private Integer controlZone_OverRun;

    @Column(name = "initiative")
    private Integer initiative;

    @Column(name = "size")
    private Integer size;

    @Column(name = "armourRange")
    private Integer armourRange;

    @Column(name = "armourMelee")
    private Integer armourMelee;

    @Column(name = "weapon1")
    private Integer weapon1;

    @Column(name = "weapon2")
    private Integer weapon2;

    @Column(name = "skillMelee")
    private Integer skillMelee;

    @Column(name = "rangedWeapon", columnDefinition = "text")
    private String rangedWeapon;

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

    @Column(name = "itemRules", columnDefinition = "text")
    private String itemRules;

    @Column(name = "isAdditionalItem", columnDefinition = "integer")
    private Boolean isAdditionalItem;

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
