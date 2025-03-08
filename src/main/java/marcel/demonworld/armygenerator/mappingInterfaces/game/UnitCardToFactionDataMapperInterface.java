package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionDataDTO> unitCardToFactionData(List<UnitCardDTO> unitList, List<AllianceAndAlternativesDTO> allyList);
}
