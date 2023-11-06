package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionDataDTO;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionDataDTO> unitCardToFactionData(List<UnitCard> unitList, List<AllianceAndAlternativesDTO> allyList);
}
