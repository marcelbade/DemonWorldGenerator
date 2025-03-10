package marcel.demonworld.armygenerator.restController;


import jakarta.validation.Valid;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.services.game.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/event")
public class EventController {

    @Autowired
    private EventService eventService;


    // TODO unfinished
    @GetMapping("/allEvents")
    public ResponseEntity<UserDTO> allEvents() {

        return null;
    }


    // TODO unfinished
    @GetMapping("/allEventsOrganizedByUser")
    public ResponseEntity<UserDTO> allEventsOrganizedByUser() {

        return null;
    }


    // TODO unfinished
    @PostMapping("/addEvent")
    public ResponseEntity<UserDTO> addEvent(@RequestBody EventDTO eventDTO) {

        return null;
    }

    // TODO unfinished
    @DeleteMapping("/deleteEvent")
    public ResponseEntity<UserDTO> deleteEvent(@RequestBody @Valid String eventName) {
        return null;
    }


}
