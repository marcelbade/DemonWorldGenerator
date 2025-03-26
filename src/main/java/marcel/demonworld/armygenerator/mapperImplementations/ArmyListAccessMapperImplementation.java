package marcel.demonworld.armygenerator.mapperImplementations;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListAccessDTO;
import marcel.demonworld.armygenerator.entities.ArmyListAccess;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListAccessMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ArmyListAccessMapperImplementation implements ArmyListAccessMapper {


    @Override
    public ArmyListAccessDTO entityToDTO(ArmyListAccess access) {
        return ArmyListAccessDTO.builder()
                .sharedList(access.getSharedList())
                .sharedWithUser(access.getSharedWithUser())
                .build();
    }

    @Override
    public ArmyListAccess dtoToEntity(ArmyListAccessDTO accessDTO) {
        return ArmyListAccess.builder()
                .sharedList(accessDTO.getSharedList())
                .sharedWithUser(accessDTO.getSharedWithUser())
                .build();
    }
}
