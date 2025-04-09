package org.example.voting.api.repository;

import org.example.voting.api.model.ConfigParameters;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<ConfigParameters, String> {
}
