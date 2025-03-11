package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.game.Event;
import marcel.demonworld.armygenerator.mappingInterfaces.game.EventMapper;
import marcel.demonworld.armygenerator.repositories.game.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    @Autowired
    EventRepository repo;

    @Autowired
    EventMapper mapper;

    public List<EventDTO> returnAll() {

        List<Event> all = repo.findAll();
        return all.stream().filter(Objects::nonNull).map(a -> mapper.entityToDto(a)).collect(Collectors.toList());
    }

    public List<EventDTO> returnAllForOrganizer(String userName) {

        List<Event> all = repo.findAllEventsForUser(userName);
        return all.stream().filter(Objects::nonNull).map(a -> mapper.entityToDto(a)).collect(Collectors.toList());
    }

    public void addEvent(EventDTO eventDTO) {
        repo.save(mapper.dtoToEntity(eventDTO));
    }

    public void deleteEvent(EventDTO eventDTO) {
        repo.delete(mapper.dtoToEntity(eventDTO));
    }



}
