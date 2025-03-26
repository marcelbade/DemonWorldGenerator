package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.ArmyList;

public interface ArmyListMapper {

    ArmyListDTO entityToDTO(ArmyList list);

    ArmyList dtoToEntity(ArmyListDTO armyListDTO);

}
