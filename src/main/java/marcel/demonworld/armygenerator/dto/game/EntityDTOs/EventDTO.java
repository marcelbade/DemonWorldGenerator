package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EventDTO {

    private Long id;
    private String eventName;
    private String url;
    private String location;
    private Date eventDate;
    private String eventOrganizer;


}

