package org.example.voting.api.controller;

import org.example.voting.api.dto.GroupDTO;
import org.example.voting.api.model.ParticipantGroup;
import org.example.voting.api.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<ParticipantGroup> createGroup(@RequestBody GroupDTO dto) {
        var group = groupService.save(dto);
        return ResponseEntity.ok(group);
    }
}
