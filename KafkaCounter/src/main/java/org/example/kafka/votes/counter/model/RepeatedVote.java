package org.example.kafka.votes.counter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class RepeatedVote {

    private String id;
    private List<Vote> votes;
    private String userEmail;

    public RepeatedVote(List<Vote> votes, String email) {
        this.votes = votes;
        this.userEmail = email;
    }

    public record Vote(Instant instant, String groupVote){}
}
