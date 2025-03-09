package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;

public interface UnitCardDtoToUnitCardMapper {

     UnitCardDTO UnitCardToUnitCardDto(UnitCard unitCard);

}
