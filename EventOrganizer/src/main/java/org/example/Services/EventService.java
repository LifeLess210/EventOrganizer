package org.example.Services;

import org.example.DTOs.CalendarDTO;
import org.example.DTOs.EventDTO;
import org.example.Entities.Event;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO getEventById(Long id);
    List<EventDTO> getAllEvents();

    EventDTO updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);

    List<Event> getEventsForWeek(Integer weekNumber, Integer year);

    CalendarDTO getCalendarForWeek(Integer weekNumber, Integer year, String username);
}