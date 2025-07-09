package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.dto.CreateEventDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventDTO;
import br.selene.projectseleneback.infra.services.EventService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(MappingEndpoint.Event.FIND_ALL)
    public Page<Event> findAll(){
        return eventService.findAll(null);
    }

    @PostMapping(MappingEndpoint.Event.CREATE)
    public Event create(@RequestBody CreateEventDTO createEventDTO ) {
        return eventService.create(createEventDTO);
    }

    @PostMapping("/{id}/" + MappingEndpoint.Event.UPDATE)
    public Event update(@PathVariable int id, @RequestBody UpdateEventDTO updateEventDTO) {
        return eventService.update(id, updateEventDTO);
    }

}
