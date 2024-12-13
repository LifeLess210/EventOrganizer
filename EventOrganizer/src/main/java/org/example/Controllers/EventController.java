package org.example.Controllers;


import org.example.DTOs.EventDTO;
import org.example.Entities.Event;
import org.example.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public String createOrUpdateEvent(@ModelAttribute EventDTO event) {
        long id;
        if (event.getId() != null && event.getId() > 0) {
            id = eventService.updateEvent(event.getId(), event).getId();
        } else {
            id = eventService.createEvent(event).getId();
        }
        return "redirect:/event/" + id;
    }

    @GetMapping("/{id}")
    public String getEvent(Model model, @PathVariable Long id) {
        EventDTO event = eventService.getEventById(id);
        if (event == null) {
            event = new EventDTO();
        }
        model.addAttribute("event", event);
        return "event";
    }
}
