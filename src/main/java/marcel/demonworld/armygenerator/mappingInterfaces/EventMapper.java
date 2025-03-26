package marcel.demonworld.armygenerator.mappingInterfaces;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.Event;

public interface EventMapper {
    EventDTO entityToDto(Event share);

    Event dtoToEntity(EventDTO eventDTO);
}
