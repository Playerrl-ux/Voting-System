package org.example.voting.api.dto;

import org.example.voting.api.model.Participant;

public record ParticipantDto(String name, Participant.Type type, short year) {

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getType(), participant.getYearOfParticipation());
    }
}
