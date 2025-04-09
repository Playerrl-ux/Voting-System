package org.example.kafka.votes.counter.repository;

import org.example.kafka.votes.counter.model.ConfigParameters;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigParamsRepository extends MongoRepository<ConfigParameters, String> {
}
