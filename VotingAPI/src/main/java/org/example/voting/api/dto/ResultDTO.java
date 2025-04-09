package org.example.voting.api.dto;

import java.util.List;

public record ResultDTO(List<String> participants, int quantityOfUniqueVotes, short percentage) {
}
