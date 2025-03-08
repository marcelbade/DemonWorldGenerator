package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.UnitCard;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allyList);
}
