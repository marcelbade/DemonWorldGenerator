package marcel.demonworld.armygenerator.mapperImplementations.game;

import lombok.RequiredArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.entities.game.AllianceAndAlternatives;
import marcel.demonworld.armygenerator.mappingInterfaces.game.AlliesAndAlternativesMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class AlliesAndAlternativesMapperImplementation implements AlliesAndAlternativesMapper {

    @Override
    public AllianceAndAlternativesDTO mapEntityToDTO(AllianceAndAlternatives alAndAlt) {

        return AllianceAndAlternativesDTO.builder()
                .ally(alAndAlt.getAlly())
                .faction(alAndAlt.getFaction())
                .hasAlternativeLists(alAndAlt.getHasAlternativeLists())
                .numberOfChoices(alAndAlt.getNumberOfChoices())
                .alternativeSubFactions(alAndAlt.getAlternativeSubFactions())
                .build();
    }
}
