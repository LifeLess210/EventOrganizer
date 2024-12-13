package org.example.DTOs;

import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long id;

    private String name;

    private String customer;

    private String address;

    private LocalDateTime start;

    private LocalDateTime till;

    @Nullable
    private UserDTO organizer;

    @Nullable
    private ParticipantsDTO participants;
}

