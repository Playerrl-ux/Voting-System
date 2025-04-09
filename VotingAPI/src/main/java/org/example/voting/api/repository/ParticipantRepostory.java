package org.example.voting.api.repository;

import org.example.voting.api.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipantRepostory extends MongoRepository<Participant, Integer> {
}
