package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.Faction;
import marcel.demonworld.armygenerator.mappingInterfaces.FactionMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class FactionMapperImplementation implements FactionMapper {
    @Override
    public FactionDTO factionToFactionDTO(Faction faction) {
        return FactionDTO.builder()
                .id(faction.getId())
                .factionName(faction.getFactionName())
                .build();
    }
}
