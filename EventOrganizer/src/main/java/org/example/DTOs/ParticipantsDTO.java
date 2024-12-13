package org.example.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantsDTO {
    private Long id;

    private List<ParticipantDTO> participant;
}


