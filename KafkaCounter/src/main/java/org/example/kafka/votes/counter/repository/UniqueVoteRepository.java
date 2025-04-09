package org.example.kafka.votes.counter.repository;

import org.example.kafka.votes.counter.model.UniqueVote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UniqueVoteRepository extends MongoRepository<UniqueVote, String> {

    Optional<UniqueVote> findByUserEmailAndGroupId(String userEmail, String groupId);
}
