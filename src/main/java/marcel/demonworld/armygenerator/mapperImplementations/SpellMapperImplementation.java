package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.entities.Spell;
import marcel.demonworld.armygenerator.mappingInterfaces.SpellMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Primary
@Component
public class SpellMapperImplementation implements SpellMapper {
    @Override
    public SpellDTO mapEntityToDTO(Spell spell) {
        return SpellDTO.builder()
                .Id(spell.getId())
                .faction(spell.getFaction())
                .spellName(spell.getSpellName())
                .spellTier(spell.getSpellTier())
                .effect(spell.getEffect())
                .requirements(spell.getRequirements())
                .duration(spell.getDuration())
                .target(spell.getTarget())
                .build();
    }

    @Override
    public Spell mapDtoToEntity(SpellDTO spellDto) {
        return Spell.builder()
                .Id(spellDto.getId())
                .faction(spellDto.getFaction())
                .spellName(spellDto.getSpellName())
                .spellTier(spellDto.getSpellTier())
                .effect(spellDto.getEffect())
                .requirements(spellDto.getRequirements())
                .duration(spellDto.getDuration())
                .target(spellDto.getTarget())
                .build();
    }
}
