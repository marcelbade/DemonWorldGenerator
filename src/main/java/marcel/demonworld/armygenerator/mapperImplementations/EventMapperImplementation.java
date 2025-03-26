package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.Event;
import marcel.demonworld.armygenerator.mappingInterfaces.EventMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Primary
public class EventMapperImplementation implements EventMapper {
    @Override
    public EventDTO entityToDto(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .eventOrganizer(event.getEventOrganizer())
                .eventName(event.getEventName())
                .location(event.getLocation())
                .url(event.getUrl())
                .eventDate(new Date(event.getEventDate().getTime()))
                .build();
    }

    @Override
    public Event dtoToEntity(EventDTO eventDTO) {
        return Event.builder()
                .id(eventDTO.getId())
                .eventOrganizer(eventDTO.getEventOrganizer())
                .eventName(eventDTO.getEventName())
                .location(eventDTO.getLocation())
                .url(eventDTO.getUrl())
                .eventDate(new java.sql.Date(eventDTO.getEventDate().getTime()))
                .build();
    }
}
