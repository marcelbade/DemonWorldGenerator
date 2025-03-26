package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.Encoding.ArmyListEncoder;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.ArmyList;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListMapper;
import marcel.demonworld.armygenerator.services.auth.UserService;
import marcel.demonworld.armygenerator.services.game.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class ArmyListMapperImplementation implements ArmyListMapper {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    ArmyListEncoder armyListEncoder;

    @Override
    public ArmyListDTO entityToDTO(ArmyList list) {
        return ArmyListDTO.builder()
                .userName(userMapper.entityToDTO(list.getUser()).getUserName())
                .listName(list.getListName())
                .list(armyListEncoder.decode(list.getList(), list.getFaction()))
                .isVisibleToOrganizer(list.getIsVisibleToOrganizer())
                .eventName(list.getEvent().getEventName())
                .faction(list.getFaction())
                .build();
    }


    @Override
    public ArmyList dtoToEntity(ArmyListDTO listDTO) {
        return ArmyList.builder()
                .user(userMapper.dtoToEntity(userService.findByUsername(listDTO.getUserName())))
                .listName(listDTO.getListName())
                .list(armyListEncoder.encode(listDTO.getList()))
                .isVisibleToOrganizer(listDTO.getIsVisibleToOrganizer())
                .event(eventService.findByEventName(listDTO.getEventName()))
                .faction(listDTO.getFaction())
                .build();
    }
}
