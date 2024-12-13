package org.example.DTOs;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HourDTO {
    String color;
    String title;
    String startTime;
    String endTime;
    String eventId;
    boolean isFirstHour;
    int duration;
}

