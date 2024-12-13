package org.example.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantDTO {
    private Long id;

    private String role;

    private UserDTO participant;
}
