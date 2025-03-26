package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.Event;
import marcel.demonworld.armygenerator.mappingInterfaces.EventMapper;
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
                .organizer(event.getEventOrganizer())
                .build();
    }

    @Override
    public Event dtoToEntity(EventDTO eventDTO) {
        return Event.builder()
                .id(eventDTO.getId())
                .eventName(eventDTO.getEventName())
                .armyLists(eventDTO.getArmyLists())
                .organizer(eventDTO.getOrganizer())
                .build();
    }
}
