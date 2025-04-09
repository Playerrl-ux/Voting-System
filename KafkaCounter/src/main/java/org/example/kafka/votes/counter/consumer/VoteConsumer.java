package org.example.kafka.votes.counter.consumer;

import org.example.kafka.votes.counter.event.VoteEvent;
import org.example.kafka.votes.counter.service.VoteService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class VoteConsumer {

    private final VoteService voteService;

    public VoteConsumer(VoteService voteService) {
        this.voteService = voteService;
    }

    @KafkaListener(topics = "votes", groupId = "${spring.kafka.consumer.group-id}",
    containerFactory = "kafkaListenerContainerFactory")
    public void listen(VoteEvent vote) {
        try{
            voteService.storeVote(vote, Instant.now());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
