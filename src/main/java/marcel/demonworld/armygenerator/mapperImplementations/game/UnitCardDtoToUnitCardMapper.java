package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class UnitCardDtoToUnitCardMapper implements marcel.demonworld.armygenerator.mappingInterfaces.game.UnitCardDtoToUnitCardMapper {


    @Override
    public UnitCardDTO UnitCardToUnitCardDto(UnitCard unitCard) {
        return UnitCardDTO.builder()
                .armourMelee(unitCard.getArmourMelee())
                .armourRange(unitCard.getArmourRange())
                .belongsToUnit(unitCard.getBelongsToUnit())
                .charge(unitCard.getCharge())
                .chargeBonus(unitCard.getChargeBonus())
                .commandStars(unitCard.getCommandStars())
                .controlZone(unitCard.getControlZone())
                .faction(unitCard.getFaction())
                .fear(unitCard.getFear())
                .hasShield(unitCard.getHasShield())
                .hitpoints(unitCard.getHitpoints())
                .hold_maneuvers(unitCard.getHold_maneuvers())
                .horde(unitCard.getHorde())
                .id(unitCard.getId())
                .initiative(unitCard.getInitiative())
                .isHighFlyer(unitCard.getIsHighFlyer())
                .isLowFlyer(unitCard.getIsLowFlyer())
                .isMultiStateUnit(unitCard.getIsMultiStateUnit())
                .isMounted(unitCard.getIsMounted())
                .leader(unitCard.getLeader())
                .leaderIsClosedOrder(unitCard.getLeaderIsClosedOrder())
                .magic(unitCard.getMagic())
                .maxFieldsMove(unitCard.getMaxFieldsMove())
                .move(unitCard.getMove())
                .moral1(unitCard.getMoral1())
                .moral2(unitCard.getMoral2())
                .multiCardName(unitCard.getMultiCardName())
                .multiStateOrderNumber(unitCard.getMultiStateOrderNumber())
                .isMultiStateUnit(unitCard.getIsMultiStateUnit())
                .multiCardName(unitCard.getMultiCardName())
                .musician(unitCard.getMusician())
                .numberOfElements(unitCard.getNumberOfElements())
                .overRun(unitCard.getOverRun())
                .points(unitCard.getPoints())
                .rangedAttackStats(unitCard.getRangedAttackStats())
                .rangedWeapon(unitCard.getRangedWeapon())
                .secondSubFaction(unitCard.getSecondSubFaction())
                .skillMelee(unitCard.getSkillMelee())
                .skillRange(unitCard.getSkillRange())
                .skirmish(unitCard.getSkirmish())
                .skirmishFormation(unitCard.getSkirmishFormation())
                .specialRules(unitCard.getSpecialRules())
                .squareFormation(unitCard.getSquareFormation())
                .standardBearer(unitCard.getStandardBearer())
                .subFaction(unitCard.getSubFaction())
                .unitName(unitCard.getUnitName())
                .unitSize(unitCard.getUnitSize())
                .unitType(unitCard.getUnitType())
                .uniqueUnit(unitCard.getUniqueUnit())
                .unitIsClosedOrder(unitCard.getUnitIsClosedOrder())
                .weapon1(unitCard.getWeapon1())
                .weapon1Name(unitCard.getWeapon1Name())
                .weapon2(unitCard.getWeapon2())
                .weapon2Name(unitCard.getWeapon2Name())
                .weapon3(unitCard.getWeapon3())
                .weapon3Name(unitCard.getWeapon3Name())
                .wedgeFormation(unitCard.getWedgeFormation())
                .build();




    }
}
