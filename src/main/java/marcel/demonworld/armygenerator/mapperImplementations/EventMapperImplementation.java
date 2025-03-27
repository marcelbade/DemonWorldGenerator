package marcel.demonworld.armygenerator.mapperImplementations;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.Event;
import marcel.demonworld.armygenerator.mappingInterfaces.EventMapper;
import marcel.demonworld.armygenerator.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Primary
public class EventMapperImplementation implements EventMapper {

    @Autowired
    UserService userService;

    @Override
    public EventDTO entityToDto(Event event) {
        return EventDTO.builder()
                .eventOrganizer(event.getEventOrganizer().getUserName())
                .eventName(event.getEventName())
                .location(event.getLocation())
                .url(event.getUrl())
                .eventDate(new Date(event.getEventDate().getTime()))
                .build();
    }

    @Override
    public Event dtoToEntity(EventDTO eventDTO) {
        return Event.builder()
                .eventOrganizer( userService.findEntityByUsername(eventDTO.getEventOrganizer()))
                .eventName(eventDTO.getEventName())
                .location(eventDTO.getLocation())
                .url(eventDTO.getUrl())
                .eventDate(new java.sql.Date(eventDTO.getEventDate().getTime()))
                .build();
    }
}
