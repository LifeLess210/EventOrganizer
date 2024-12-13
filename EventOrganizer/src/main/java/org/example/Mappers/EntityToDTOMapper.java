package org.example.Mappers;

import org.example.DTOs.EventDTO;
import org.example.DTOs.ParticipantDTO;
import org.example.DTOs.ParticipantsDTO;
import org.example.DTOs.UserDTO;
import org.example.Entities.Event;
import org.example.Entities.Participant;
import org.example.Entities.Participants;
import org.example.Entities.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityToDTOMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }

    public static ParticipantDTO toParticipantDTO(Participant participant) {
        if (participant == null) {
            return null;
        }

        return ParticipantDTO.builder()
                .id(participant.getId())
                .role(participant.getRole())
                .participant(toUserDTO(participant.getParticipant()))
                .build();
    }

    public static ParticipantsDTO toParticipantsDTO(Participants participants) {
        if (participants == null) {
            return null;
        }

        List<ParticipantDTO> participantDTOs = Optional.ofNullable(participants.getParticipants())
                .orElse(List.of())
                .stream()
                .map(EntityToDTOMapper::toParticipantDTO)
                .collect(Collectors.toList());

        return ParticipantsDTO.builder()
                .id(participants.getId())
                .participant(participantDTOs)
                .build();
    }

    public static EventDTO toEventDTO(Event event) {
        if (event == null) {
            return null;
        }

        return EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .customer(event.getCustomer())
                .address(event.getAddress())
                .start(event.getStart())
                .till(event.getTill())
                .organizer(toUserDTO(Optional.ofNullable(event.getOrganizer()).orElse(null)))
                .participants(toParticipantsDTO(event.getParticipants()))
                .build();
    }
}
