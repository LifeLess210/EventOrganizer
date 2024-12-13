package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ParticipantsGroup")
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colorCode;

    @OneToMany(mappedBy = "participantsGroup")
    private List<Participant> participants;
}
