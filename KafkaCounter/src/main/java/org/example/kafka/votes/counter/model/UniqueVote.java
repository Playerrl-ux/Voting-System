package org.example.kafka.votes.counter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UniqueVote {

    private String id;
    private String userEmail;
    private String groupId;
    @CreatedDate
    private Instant timestamp;

    public UniqueVote(String email, String groupId, Instant timestamp) {
        this.userEmail = email;
        this.groupId = groupId;
        this.timestamp = timestamp;
    }
}
