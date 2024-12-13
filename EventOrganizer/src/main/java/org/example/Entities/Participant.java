package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "Participants")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User participant;

    @ManyToOne
    @JoinColumn(name = "participants_group_id")
    private Participants participantsGroup;
}
