package org.example.kafka.votes.counter.service;

import org.example.kafka.votes.counter.cache.ParticipantGroups;
import org.example.kafka.votes.counter.model.ConfigParameters;
import org.example.kafka.votes.counter.repository.ConfigParamsRepository;
import org.example.kafka.votes.counter.repository.ParticipantGroupsRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InitializerService {

    private final ConfigParamsRepository configParamsRepository;
    private final ParticipantGroupsRepository participantGroupsRepository;

    public InitializerService(ConfigParamsRepository configParamsRepository,
                              ParticipantGroupsRepository participantGroupsRepository) {
        this.configParamsRepository = configParamsRepository;
        this.participantGroupsRepository = participantGroupsRepository;
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            participantGroupsRepository.deleteAll();
            Optional<ConfigParameters> search = configParamsRepository.findById("validVoting");
            if(search.isPresent()) {
                List<String> ids = Arrays.stream(search.get().getValue()
                        .split(",")).toList();
                List<ParticipantGroups> groups = ids.stream().map(ParticipantGroups::new).toList();
                participantGroupsRepository.saveAll(groups);
            }
        };
    }


}
