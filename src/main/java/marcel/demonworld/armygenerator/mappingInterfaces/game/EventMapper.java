package marcel.demonworld.armygenerator.mappingInterfaces.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.entities.game.Event;

public interface EventMapper {
    EventDTO entityToDto(Event share);

    Event dtoToEntity(EventDTO eventDTO);
}
