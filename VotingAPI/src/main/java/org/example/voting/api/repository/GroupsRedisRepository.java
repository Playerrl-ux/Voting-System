package org.example.voting.api.repository;

import org.example.voting.api.redis.model.ParticipantGroupsRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRedisRepository extends CrudRepository<ParticipantGroupsRedis, String> {
}
