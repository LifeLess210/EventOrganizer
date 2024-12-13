package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Customer cannot be null")
    private String customer;

    @NotNull
    private String address;

    @NotNull
    @Column(name = "start_time")
    private LocalDateTime start;

    @NotNull
    @Column(name = "end_time")
    private LocalDateTime till;

    @OneToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    private String colorCode;

    @OneToOne
    @JoinColumn(name = "participants_group_id")
    private Participants participants;
}
