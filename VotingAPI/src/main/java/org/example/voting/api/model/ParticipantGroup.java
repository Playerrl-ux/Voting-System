package org.example.voting.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
@Document
public class ParticipantGroup {

    private String id;
    private Map<String, List<String>> participantGroupsIds;
    private Timestamp createdAt;
    public ParticipantGroup(Map<String, List<String>> ids, Timestamp timestamp) {
        this.participantGroupsIds = ids;
        this.createdAt = timestamp;
    }

}
