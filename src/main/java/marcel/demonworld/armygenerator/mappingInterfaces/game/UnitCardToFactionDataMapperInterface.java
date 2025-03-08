package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCard;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allyList);
}
