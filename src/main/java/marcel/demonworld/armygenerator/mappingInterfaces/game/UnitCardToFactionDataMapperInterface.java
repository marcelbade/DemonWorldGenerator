package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.alliancesDTO.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.game.factionDataDTO.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.game.statCardDTOs.UnitCard;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allyList);
}
