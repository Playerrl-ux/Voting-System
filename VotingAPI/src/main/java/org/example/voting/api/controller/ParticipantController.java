package org.example.voting.api.controller;

import org.example.voting.api.dto.ParticipantDto;
import org.example.voting.api.model.Participant;
import org.example.voting.api.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("participants")
public class ParticipantController {

    private final ParticipantRepository participantRepostory;

    public ParticipantController(ParticipantRepository participantRepostory) {
        this.participantRepostory = participantRepostory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getParticipant(@PathVariable("id") String id) {
        Optional<Participant> search  = participantRepostory.findById(id);
        if (search.isPresent()) {
            return ResponseEntity.ok(ParticipantDto.from(search.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> saveConfigParameters(@RequestBody ParticipantDto dto) {
        System.out.println(dto.toString());
        var participant = new Participant(dto.name(), dto.type(), dto.year());
        participant = participantRepostory.save(participant);
        return ResponseEntity.ok(ParticipantDto.from(participant));
    }
}
