package org.example.voting.api.dto;
import java.util.List;

public record GroupDTO(List<List<String>> ids, short numberOfVoting) {

}
