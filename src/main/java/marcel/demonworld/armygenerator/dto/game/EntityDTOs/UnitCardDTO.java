package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;
import marcel.demonworld.armygenerator.dto.game.CardInterface.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.EquipmentTypes;

import java.util.List;

/**
 * one dto for all army cards, no matter what the unit type is.
 * Equipped Items are store in a simple list<ItemCard>
 */
@Data
@Builder
public class UnitCardDTO implements DemonWorldCard {

    private Integer Id;

    private String faction;

    private String unitName;

    private String rangedWeapon;

    private String rangedAttackStats;

    private Boolean uniqueUnit;

    private Boolean isMounted;

    private Boolean leader;

    private Boolean standardBearer;

    private Boolean musician;

    private String unitType;

    private Boolean wedgeFormation;

    private Boolean skirmishFormation;

    private Boolean squareFormation;

    private Boolean horde;

    private Integer commandStars;

    private Integer magic;

    private String subFaction;

    private Integer points;

    private Integer numberOfElements;

    private Integer move;

    private Integer charge;

    private Integer skirmish;

    private Integer hold_maneuvers;

    private Integer controlZone;

    private Integer overRun;

    private Integer initiative;

    private Integer unitSize;

    private Integer armourRange;

    private Integer armourMelee;

    private String weapon1Name;

    private Integer weapon1;

    private String weapon2Name;

    private Integer weapon2;

    private String weapon3Name;

    private Integer weapon3;

    private Integer skillMelee;

    private Integer skillRange;

    private Integer fear;

    private Integer moral1;

    private Integer moral2;

    private Integer hitpoints;

    private String specialRules;

    private Boolean isHighFlyer;

    private Boolean isLowFlyer;

    private Boolean hasShield;

    private Integer chargeBonus;

    private String secondSubFaction;

    private Boolean isMultiStateUnit;

    private String belongsToUnit;

    private Integer multiStateOrderNumber;

    private Boolean leaderIsClosedOrder;

    private Boolean unitIsClosedOrder;

    private String multiCardName;

    private Boolean maxFieldsMove;

    private Boolean isCustom;

    private String createdBy;

    private Boolean isDeleted;

    private List<ItemCardDTO> equipment;

    private EquipmentTypes equipmentTypes;

    private final Integer lossCounter = 0;

    private final Boolean unitDestroyed = false;

    private Integer maxCounter;

    @Override
    public String getName() { //
        return this.unitName;
    }

    @Override
    public String getSubFaction() { //
        return this.subFaction;
    }

    @Override
    public int getPoints() { //
        return this.points;
    }

    public void setMaxCounter() { //
        this.maxCounter = this.numberOfElements * this.hitpoints;
    }

}
