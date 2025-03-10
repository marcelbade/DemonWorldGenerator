package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.game.ArmyList;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ArmyListMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class ArmyListMapperImplementation implements ArmyListMapper {


    @Override
    public ArmyListDTO entityToDTO(ArmyList list) {
        return ArmyListDTO.builder()
                .id(list.getId())
                .userName(list.getUser().getUserName())
                .armyListName(list.getListName())
                .armyList(list.getList())
                .isPublicForOrganizer(list.getIsPublicForOrganizer())
                .event(list.getEvent())
                .build();
    }
}
