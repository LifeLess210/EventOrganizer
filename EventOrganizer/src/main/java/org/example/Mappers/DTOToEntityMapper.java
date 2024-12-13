package org.example.Mappers;

import org.example.DTOs.ParticipantDTO;
import org.example.DTOs.ParticipantsDTO;
import org.example.DTOs.UserDTO;
import org.example.Entities.Event;
import org.example.Entities.Participant;
import org.example.Entities.Participants;
import org.example.Entities.User;
import org.example.DTOs.EventDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOToEntityMapper {

    public static User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .id(userDTO.getId())
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .password(userDTO.getPassword())
                .build();
    }

    public static Participant toParticipant(ParticipantDTO participantDTO) {
        if (participantDTO == null) {
            return null;
        }

        return Participant.builder()
                .id(participantDTO.getId())
                .role(participantDTO.getRole())
                .participant(toUser(participantDTO.getParticipant()))
                .build();
    }

    public static Participants toParticipants(ParticipantsDTO participantsDTO) {
        if (participantsDTO == null) {
            return null;
        }

        List<Participant> participants = participantsDTO.getParticipant().stream()
                .map(DTOToEntityMapper::toParticipant)
                .collect(Collectors.toList());

        return Participants.builder()
                .id(participantsDTO.getId())
                .participants(participants)
                .build();
    }

    public static Event toEvent(EventDTO eventDTO) {
        if (eventDTO == null) {
            return null;
        }

        return Event.builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .customer(eventDTO.getCustomer())
                .address(eventDTO.getAddress())
                .start(eventDTO.getStart())
                .till(eventDTO.getTill())
                .organizer(toUser(eventDTO.getOrganizer()))
                .participants(toParticipants(eventDTO.getParticipants()))
                .build();
    }
}
