package org.example.voting.api.controller;

import org.example.voting.api.dto.GroupDTO;
import org.example.voting.api.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDTO dto) {
        groupService.save(dto);
        return ResponseEntity.ok().build();
    }
}
