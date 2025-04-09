package org.example.voting.api.repository;

import org.example.voting.api.model.ParticipantGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<ParticipantGroup, String> {
}
