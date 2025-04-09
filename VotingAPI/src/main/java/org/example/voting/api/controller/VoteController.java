package org.example.voting.api.controller;

import org.example.voting.api.dto.VoteEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("votes")
public class VoteController {

    private final String topic = "votes";
    private final KafkaTemplate<String, VoteEvent> kafkaProducer;

    public VoteController(KafkaTemplate<String, VoteEvent> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteEvent voteEvent) {
        kafkaProducer.send(topic, voteEvent);
        return ResponseEntity.ok().build();
    }
}
