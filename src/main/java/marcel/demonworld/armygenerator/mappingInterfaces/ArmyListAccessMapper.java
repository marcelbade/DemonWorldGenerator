package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListAccessDTO;
import marcel.demonworld.armygenerator.entities.ArmyListAccess;

public interface ArmyListAccessMapper {
    ArmyListAccessDTO entityToDTO(ArmyListAccess access);

    ArmyListAccess dtoToEntity(ArmyListAccessDTO armyListAccessDTO);

}
