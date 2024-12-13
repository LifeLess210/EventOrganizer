package org.example.Services.Implementations;

import org.example.DTOs.CalendarDTO;
import org.example.DTOs.DayDTO;
import org.example.DTOs.HourDTO;
import org.example.Entities.Event;
import org.example.DTOs.EventDTO;
import org.example.Mappers.DTOToEntityMapper;
import org.example.Mappers.EntityToDTOMapper;
import org.example.Repositories.EventRepository;
import org.example.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = DTOToEntityMapper.toEvent(eventDTO);
        event = eventRepository.save(event);
        return EntityToDTOMapper.toEventDTO(event);
    }

    @Override
    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(EntityToDTOMapper::toEventDTO)
                .orElse(null);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EntityToDTOMapper::toEventDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existingEvent.setName(eventDTO.getName());
        existingEvent.setCustomer(eventDTO.getCustomer());
        existingEvent.setAddress(eventDTO.getAddress());
        existingEvent.setStart(eventDTO.getStart());
        existingEvent.setTill(eventDTO.getTill());

        Event updatedEvent = eventRepository.save(existingEvent);
        return EntityToDTOMapper.toEventDTO(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {

    }

    public CalendarDTO getCalendarForWeek(Integer weekNumber, Integer year, String userName) {
        LocalDate firstDayOfWeek = LocalDate.now()
                .withYear(year)
                .with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekNumber)
                .with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());

        List<DayDTO> days = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd");

        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = firstDayOfWeek.plusDays(i);
            List<HourDTO> hours = new ArrayList<>(24);
            for (int j = 0; j < 24; j++) {
                hours.add(new HourDTO());
            }
            DayDTO dayDTO = new DayDTO(currentDay, currentDay.format(formatter), hours);
            days.add(dayDTO);
        }

        List<Event> events = getEventsForWeek(weekNumber-1, year);

        for (Event event : events) {
            LocalDateTime start = event.getStart();
            LocalDateTime end = event.getTill();

            LocalDate startDay = start.toLocalDate();
            LocalDate endDay = end.toLocalDate();

            int startDayIndex = (int) ChronoUnit.DAYS.between(firstDayOfWeek, startDay);
            int endDayIndex = (int) ChronoUnit.DAYS.between(firstDayOfWeek, endDay);

            startDayIndex = Math.max(startDayIndex, 0);
            endDayIndex = Math.min(endDayIndex, 6);

            int totalDurationInHours = (int) ChronoUnit.HOURS.between(start, end);

            for (int dayIndex = startDayIndex; dayIndex <= endDayIndex; dayIndex++) {
                int startHour = (dayIndex == startDayIndex) ? start.getHour() : 0;
                int endHour = (dayIndex == endDayIndex) ? end.getHour() : 23;

                if (dayIndex == startDayIndex) {
                    int durationForFirstDay = (endHour - startHour + 1);
                    for (int hour = startHour; hour <= endHour; hour++) {
                        HourDTO hourDTO = new HourDTO();
                        hourDTO.setColor(event.getColorCode());
                        hourDTO.setTitle(event.getName());
                        hourDTO.setStartTime(start.toLocalTime().toString());
                        hourDTO.setEndTime(end.toLocalTime().toString());
                        hourDTO.setEventId(event.getId().toString());

                        if (hour == startHour) {
                            hourDTO.setFirstHour(true);
                            hourDTO.setDuration(durationForFirstDay);
                        } else {
                            hourDTO.setFirstHour(false);
                            hourDTO.setDuration(0);
                        }

                        days.get(dayIndex).getHours().set(hour, hourDTO);
                    }
                } else {
                    for (int hour = startHour; hour <= endHour; hour++) {
                        HourDTO hourDTO = new HourDTO();
                        hourDTO.setColor(event.getColorCode());
                        hourDTO.setTitle(event.getName());
                        hourDTO.setStartTime(start.toLocalTime().toString());
                        hourDTO.setEndTime(end.toLocalTime().toString());
                        hourDTO.setEventId(event.getId().toString());

                        if (hour == startHour) {
                            hourDTO.setFirstHour(true);
                            hourDTO.setDuration(endHour - startHour + 1);
                        } else {
                            hourDTO.setFirstHour(false);
                            hourDTO.setDuration(0);
                        }

                        days.get(dayIndex).getHours().set(hour, hourDTO);
                    }
                }
            }
        }

        CalendarDTO calendarDTO = new CalendarDTO();
        calendarDTO.setDays(days);
        return calendarDTO;
    }



    @Override
    public List<Event> getEventsForWeek(Integer weekNumber, Integer year) {
        return eventRepository.getEventsForWeek(weekNumber, year);
    }
}
