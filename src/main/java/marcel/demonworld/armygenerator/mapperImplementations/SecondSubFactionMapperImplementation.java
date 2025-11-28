package marcel.demonworld.armygenerator.mapperImplementations;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SecondSubFactionDTO;
import marcel.demonworld.armygenerator.entities.SecondSubFaction;
import marcel.demonworld.armygenerator.mappingInterfaces.SecondSubFactionMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SecondSubFactionMapperImplementation implements SecondSubFactionMapper {

    @Override
    public SecondSubFactionDTO entityToDTO(SecondSubFaction secondSubFaction) {
        return SecondSubFactionDTO
                .builder()
                .id(secondSubFaction.getId())
                .faction(secondSubFaction.getFaction().getFactionName())
                .secondSubFaction(secondSubFaction.getSecondSubFaction())
                .build();
    }
}
