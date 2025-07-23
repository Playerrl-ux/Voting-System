package org.example.voting.api.dto;

import org.example.voting.api.model.Participant;

public record ParticipantDto(String id, String name, Participant.Type type, short year) {

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getId(), participant.getName(), participant.getType(), participant.getYearOfParticipation());
    }
}
