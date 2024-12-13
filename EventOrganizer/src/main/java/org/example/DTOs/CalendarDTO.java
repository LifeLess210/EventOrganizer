package org.example.DTOs;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {
    private List<DayDTO> days;
    private String firstDayOfTheWeek;
    private String lastDayOfTheWeek;
    private String userName;
}
