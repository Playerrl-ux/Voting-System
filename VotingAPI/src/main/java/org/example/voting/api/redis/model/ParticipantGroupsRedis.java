package org.example.voting.api.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("ParticipantGroups")
@Data
@AllArgsConstructor
public class ParticipantGroupsRedis {

    @Id
    private String participantGroupId;
    private List<String> participantsIds;
}
