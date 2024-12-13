package org.example.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayDTO {
    private LocalDate date;
    private String formattedDate;
    private List<HourDTO> hours;
}
