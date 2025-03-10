package marcel.demonworld.armygenerator.mapperImplementations.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.game.Event;
import marcel.demonworld.armygenerator.mappingInterfaces.game.EventMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class EventMapperImplementation implements EventMapper {
    @Override
    public EventDTO entityToDto(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .armyLists(event.getArmyLists())
                .organizer(event.getOrganizer())
                .build();
    }
}
