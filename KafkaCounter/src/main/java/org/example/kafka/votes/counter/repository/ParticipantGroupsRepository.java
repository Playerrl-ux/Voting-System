package org.example.kafka.votes.counter.repository;

import org.example.kafka.votes.counter.cache.ParticipantGroups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantGroupsRepository extends CrudRepository<ParticipantGroups, String> {
}
