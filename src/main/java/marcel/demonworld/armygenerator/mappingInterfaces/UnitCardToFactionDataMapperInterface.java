package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.FactionDataDTO.FactionData;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UnitCardToFactionDataMapperInterface {
    List<FactionData> unitCardToFactionData(List<UnitCard> list);
}
