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
    public ItemCardDTO itemCardToItemCardDto(ItemCard itemCard) {
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
                .build();
    }
}
