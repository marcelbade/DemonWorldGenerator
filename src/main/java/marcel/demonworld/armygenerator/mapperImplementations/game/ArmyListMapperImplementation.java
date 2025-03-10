package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.game.ArmyList;
import marcel.demonworld.armygenerator.mapperImplementations.auth.UserMapper;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ArmyListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class ArmyListMapperImplementation implements ArmyListMapper {

    @Autowired
    UserMapper userMapper;


    @Override
    public ArmyListDTO entityToDTO(ArmyList list) {
        return ArmyListDTO.builder()
                .id(list.getId())
                .user(userMapper.entityToDTO(list.getUser()))
                .listName(list.getListName())
                .list(list.getList())
                .isVisibleToOrganizer(list.getIsVisibleToOrganizer())
                .event(list.getEvent())
                .build();
    }

    @Override
    public ArmyList dtoToEntity(ArmyListDTO listDTO) {
        return ArmyList.builder()
                .id(listDTO.getId())
                .user(userMapper.dtoToEntity(listDTO.getUser()))
                .listName(listDTO.getListName())
                .list(listDTO.getList())
                .isVisibleToOrganizer(listDTO.getIsVisibleToOrganizer())
                .event(listDTO.getEvent())
                .build();
    }
}
