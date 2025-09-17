package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionColorDTO;
import marcel.demonworld.armygenerator.entities.FactionColor;
import marcel.demonworld.armygenerator.mappingInterfaces.FactionColorMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class FactionColorMapperImplementation implements FactionColorMapper {

    @Override
    public FactionColorDTO mapEntityToDTO(FactionColor factionColor) {
        return FactionColorDTO
                .builder()
                .faction(factionColor.getFaction().getFactionName())
                .rgbA(factionColor.getRgbA())
                .rgbB(factionColor.getRgbB())
                .rgbC(factionColor.getRgbC())
                .build();
    }
}
