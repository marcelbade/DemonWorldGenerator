package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.Encoding.ArmyListEncoder;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.ArmyList;
import marcel.demonworld.armygenerator.entities.ArmyListAccess;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListAccessMapper;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListMapper;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;


@Component
@Primary
public class ArmyListMapperImplementation implements ArmyListMapper {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArmyListAccessMapper armyListAccessMapper;

    @Autowired
    UserService userService;

    @Autowired
    ArmyListEncoder armyListEncoder;

    @Override
    public ArmyListDTO entityToDTO(ArmyList list) {
        return ArmyListDTO.builder()
                .userName(list.getUser().getUserName())
                .listName(list.getListName())
                .faction(list.getFaction())
                .list(armyListEncoder.decode(list.getList(), list.getFaction()))
                .eventName(list.getEventName())
                .creationDate(list.getCreationDate())
                .teamName(list.getTeamName())
                .userWithAccess(list.getUsersWithAccess()
                        .stream()
                        .map(ArmyListAccess::getSharedWithUser)
                        .collect(Collectors.toList()))
                .build();
    }


    @Override
    public ArmyList dtoToEntity(ArmyListDTO listDTO) {
        return ArmyList.builder()
                .user(userMapper.dtoToEntity(userService.findByUsername(listDTO.getUserName())))
                .listName(listDTO.getListName())
                .list(armyListEncoder.encode(listDTO.getList()))
                .eventName(listDTO.getEventName())
                .faction(listDTO.getFaction())
                .creationDate(new java.sql.Date(listDTO.getCreationDate().getTime()))
                .teamName(listDTO.getTeamName())
                .usersWithAccess(Collections.emptyList())
                .build();
    }
}
