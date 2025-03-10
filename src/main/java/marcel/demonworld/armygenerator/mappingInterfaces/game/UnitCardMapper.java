package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;

public interface UnitCardMapper {

     UnitCardDTO entityToDto(UnitCard unitCard);
     UnitCard dtoToEntity(UnitCardDTO unitCardDTO);
}
