package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ListShareDTO;
import marcel.demonworld.armygenerator.entities.game.ListShare;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ListShareMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class ListShareMapperImplementation implements ListShareMapper {
    @Override
    public ListShareDTO entityToDto(ListShare share) {
        return ListShareDTO.builder()
                .ListName(share.getUserName())
                .userName(share.getUserName())
                .build();
    }
}
