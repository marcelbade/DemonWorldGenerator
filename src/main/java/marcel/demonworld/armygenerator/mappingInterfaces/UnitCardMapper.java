package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.UnitCard;

public interface UnitCardMapper {

     UnitCardDTO entityToDto(UnitCard unitCard);
     UnitCard dtoToEntity(UnitCardDTO unitCardDTO);
}
