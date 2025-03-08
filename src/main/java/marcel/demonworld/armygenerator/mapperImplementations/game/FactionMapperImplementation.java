package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.game.Faction;
import marcel.demonworld.armygenerator.mappingInterfaces.game.FactionMapper;
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
