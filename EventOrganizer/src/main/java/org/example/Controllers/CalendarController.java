package org.example.Controllers;

import org.example.DTOs.CalendarDTO;
import org.example.Entities.Event;
import org.example.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String loadCalendar(@RequestParam(value = "week", required = false) Integer weekNumber,
                               @RequestParam(value = "year", required = false) Integer year,
                               Model model, Principal principal) {

        if (weekNumber == null) {
            weekNumber = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        }
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String username = principal.getName();

        CalendarDTO calendar = eventService.getCalendarForWeek(weekNumber, year, username);

        model.addAttribute("calendar", calendar);
        model.addAttribute("weekNumber", weekNumber);
        model.addAttribute("year", year);

        return "calendar";
    }


}
