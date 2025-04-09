package org.example.voting.api.service;

import org.example.voting.api.dto.GroupDTO;
import org.example.voting.api.model.ConfigParameters;
import org.example.voting.api.model.ParticipantGroup;
import org.example.voting.api.repository.ConfigRepository;
import org.example.voting.api.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final ConfigRepository configRepository;

    public GroupService(GroupRepository groupRepository, ConfigRepository configRepository) {
        this.groupRepository = groupRepository;
        this.configRepository = configRepository;
    }

    public void save(GroupDTO dto) {

        Map<String, List<String>> ids = dto.ids().stream()
                .collect(Collectors.toMap(k -> UUID.randomUUID().toString(), v -> v));

        var participantGroup = new ParticipantGroup(ids, new Timestamp(System.currentTimeMillis()));
        participantGroup = groupRepository.save(participantGroup);

        configRepository.save(new ConfigParameters("validVoting", String.join(",", ids.keySet()),
                ConfigParameters.Type.ListString));

    }

}
