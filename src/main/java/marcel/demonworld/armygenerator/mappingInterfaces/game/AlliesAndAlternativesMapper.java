package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.entities.game.AllianceAndAlternatives;

public interface AlliesAndAlternativesMapper {


    AllianceAndAlternativesDTO mapEntityToDTO (AllianceAndAlternatives AlAndAlt);

}
