package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.entities.AllianceAndAlternatives;

public interface AlliesAndAlternativesMapper {


    AllianceAndAlternativesDTO mapEntityToDTO (AllianceAndAlternatives AlAndAlt);

}
