package marcel.demonworld.armygenerator.controllers;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.EventDTO;
import marcel.demonworld.armygenerator.services.game.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping("/allEvents")
    public List<EventDTO> allEvents() {
        return eventService.returnAll();
    }

    @GetMapping("/allEventsOrganizedByUser")
    public List<EventDTO> allEventsOrganizedByUser(@RequestParam String organizer) {

        return eventService.returnAllForOrganizer(organizer);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody EventDTO eventDTO) {

        eventService.addEvent(eventDTO);
        return ResponseEntity.ok(eventDTO.getEventName());
    }

    @DeleteMapping("/deleteEvent")
    public ResponseEntity<String> deleteEvent(@RequestBody EventDTO eventDTO) {

        eventService.deleteEvent(eventDTO);
        return ResponseEntity.ok(eventDTO.getEventName());
    }


}
