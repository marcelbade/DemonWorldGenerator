package marcel.demonworld.armygenerator.dto.game.EntityDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.dto.game.CardInterface.DemonWorldCard;


@Setter
@Getter
@Builder

public class ItemCardDTO implements DemonWorldCard {


    private Integer id;

    private String faction;

    private String itemName;

    private Boolean isGeneric;

    private Boolean requiresShield;

    private Boolean mustBeMounted;

    private Boolean usableByCav;

    private Integer maxRangeArmor;

    private Integer maxSize;

    private String subfaction;

    private Boolean everyElement;

    private String limitedToUnit;

    private Boolean magicUsersOnly;

    private String unitType;

    private String itemType;

    private String requiresWeaponType;

    private Boolean requiresBanner;

    private Boolean requiresMusician;

    private Integer pointCost;

    private Integer move;

    private Integer charge;

    private Integer skirmish;

    private Integer hold_maneuvers;

    private Integer controlZone_OverRun;

    private Integer initiative;

    private Integer size;

    private Integer armourRange;

    private Integer armourMelee;

    private Integer weapon1;

    private Integer weapon2;

    private Integer skillMelee;

    private String rangedWeapon;

    private Integer skillRange;

    private Integer fear;

    private Integer moral1;

    private Integer moral2;

    private Integer hitpoints;

    private String itemRules;

    private Boolean isAdditionalItem;

    @Override
    public int getPoints() { //
        return this.pointCost;
    }

    @Override
    public String getName() { //
        return this.itemName;
    }

    @Override
    public String getSubFaction() { //
        return null;
    }


}
