package org.example.kafka.votes.counter.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("PaticipantGroups")
@Data
@AllArgsConstructor
public class ParticipantGroups implements Serializable {

    @Id
    private String participantGroupId;


}
