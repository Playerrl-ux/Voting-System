package org.example.voting.api.dto;
import org.example.voting.api.model.ParticipantGroup;

import java.util.List;

public record GroupDTO(List<List<String>> ids, short numberOfVoting) {

}
