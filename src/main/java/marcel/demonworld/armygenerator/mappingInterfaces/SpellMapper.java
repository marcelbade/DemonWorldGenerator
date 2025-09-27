package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.entities.Spell;

public interface SpellMapper {

    SpellDTO mapEntityToDTO(Spell spell);

    Spell mapDtoToEntity(SpellDTO spellDto);
}
