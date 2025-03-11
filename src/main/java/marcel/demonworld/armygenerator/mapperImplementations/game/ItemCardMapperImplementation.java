package marcel.demonworld.armygenerator.mapperImplementations.game;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import marcel.demonworld.armygenerator.entities.game.ItemCard;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ItemCardMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ItemCardMapperImplementation implements ItemCardMapper {
    @Override
    public ItemCardDTO entityToDTO(ItemCard itemCard) {
        return ItemCardDTO.builder()
                .id(itemCard.getId())
                .armourMelee(itemCard.getArmourMelee())
                .armourRange(itemCard.getArmourRange())
                .charge(itemCard.getCharge())
                .controlZone_OverRun(itemCard.getControlZone_OverRun())
                .everyElement(itemCard.getEveryElement())
                .faction(itemCard.getFaction())
                .fear(itemCard.getFear())
                .hitpoints(itemCard.getHitpoints())
                .itemName(itemCard.getItemName())
                .itemRules(itemCard.getItemRules())
                .itemType(itemCard.getItemType())
                .initiative(itemCard.getInitiative())
                .isGeneric(itemCard.getIsGeneric())
                .hold_maneuvers(itemCard.getHold_maneuvers())
                .isAdditionalItem(itemCard.getIsAdditionalItem())
                .limitedToUnit(itemCard.getLimitedToUnit())
                .maxRangeArmor(itemCard.getMaxRangeArmor())
                .magicUsersOnly(itemCard.getMagicUsersOnly())
                .maxSize(itemCard.getMaxSize())
                .moral1(itemCard.getMoral1())
                .moral2(itemCard.getMoral2())
                .move(itemCard.getMove())
                .mustBeMounted(itemCard.getMustBeMounted())
                .pointCost(itemCard.getPointCost())
                .rangedWeapon(itemCard.getRangedWeapon())
                .requiresBanner(itemCard.getRequiresBanner())
                .requiresMusician(itemCard.getRequiresMusician())
                .requiresShield(itemCard.getRequiresShield())
                .requiresWeaponType(itemCard.getRequiresWeaponType())
                .size(itemCard.getSize())
                .skillMelee(itemCard.getSkillMelee())
                .skillRange(itemCard.getSkillRange())
                .skirmish(itemCard.getSkirmish())
                .subfaction(itemCard.getSubfaction())
                .usableByCav(itemCard.getUsableByCav())
                .unitType(itemCard.getUnitType())
                .weapon1(itemCard.getWeapon1())
                .weapon2(itemCard.getWeapon2())
                .isDeleted(itemCard.getIsDeleted())
                .build();
    }

    @Override
    public ItemCard DtoToEntity(ItemCardDTO itemCardDTO) {
        return ItemCard.builder()
                .id(itemCardDTO.getId())
                .armourMelee(itemCardDTO.getArmourMelee())
                .armourRange(itemCardDTO.getArmourRange())
                .charge(itemCardDTO.getCharge())
                .controlZone_OverRun(itemCardDTO.getControlZone_OverRun())
                .everyElement(itemCardDTO.getEveryElement())
                .faction(itemCardDTO.getFaction())
                .fear(itemCardDTO.getFear())
                .hitpoints(itemCardDTO.getHitpoints())
                .itemName(itemCardDTO.getItemName())
                .itemRules(itemCardDTO.getItemRules())
                .itemType(itemCardDTO.getItemType())
                .initiative(itemCardDTO.getInitiative())
                .isGeneric(itemCardDTO.getIsGeneric())
                .hold_maneuvers(itemCardDTO.getHold_maneuvers())
                .isAdditionalItem(itemCardDTO.getIsAdditionalItem())
                .limitedToUnit(itemCardDTO.getLimitedToUnit())
                .maxRangeArmor(itemCardDTO.getMaxRangeArmor())
                .magicUsersOnly(itemCardDTO.getMagicUsersOnly())
                .maxSize(itemCardDTO.getMaxSize())
                .moral1(itemCardDTO.getMoral1())
                .moral2(itemCardDTO.getMoral2())
                .move(itemCardDTO.getMove())
                .mustBeMounted(itemCardDTO.getMustBeMounted())
                .pointCost(itemCardDTO.getPointCost())
                .rangedWeapon(itemCardDTO.getRangedWeapon())
                .requiresBanner(itemCardDTO.getRequiresBanner())
                .requiresMusician(itemCardDTO.getRequiresMusician())
                .requiresShield(itemCardDTO.getRequiresShield())
                .requiresWeaponType(itemCardDTO.getRequiresWeaponType())
                .size(itemCardDTO.getSize())
                .skillMelee(itemCardDTO.getSkillMelee())
                .skillRange(itemCardDTO.getSkillRange())
                .skirmish(itemCardDTO.getSkirmish())
                .subfaction(itemCardDTO.getSubFaction())
                .usableByCav(itemCardDTO.getUsableByCav())
                .unitType(itemCardDTO.getUnitType())
                .weapon1(itemCardDTO.getWeapon1())
                .weapon2(itemCardDTO.getWeapon2())
                .isDeleted(itemCardDTO.getIsDeleted())
                .build();
    }
}
