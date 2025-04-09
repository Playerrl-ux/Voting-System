package org.example.kafka.votes.counter.repository;

import org.example.kafka.votes.counter.model.RepeatedVote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepeatedVoteRepository extends MongoRepository<RepeatedVote, String> {

    Optional<RepeatedVote> findByUserEmail(String userEmail);
}
