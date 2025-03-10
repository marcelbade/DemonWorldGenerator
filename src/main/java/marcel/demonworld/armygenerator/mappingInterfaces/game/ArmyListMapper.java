package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.game.ArmyList;

public interface ArmyListMapper {

    ArmyListDTO entityToDTO(ArmyList list);

    ArmyList dtoToEntity(ArmyListDTO armyListDTO);

}
