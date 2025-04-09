package org.example.kafka.votes.counter.service;

import org.example.kafka.votes.counter.cache.ParticipantGroups;
import org.example.kafka.votes.counter.event.VoteEvent;
import org.example.kafka.votes.counter.model.RepeatedVote;
import org.example.kafka.votes.counter.model.UniqueVote;
import org.example.kafka.votes.counter.repository.ParticipantGroupsRepository;
import org.example.kafka.votes.counter.repository.RepeatedVoteRepository;
import org.example.kafka.votes.counter.repository.UniqueVoteRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final RepeatedVoteRepository repeatedVoteRepository;
    private final UniqueVoteRepository uniqueVoteRepository;
    private final ParticipantGroupsRepository participantGroupsRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public VoteService(RepeatedVoteRepository repeatedVoteRepository,
                       UniqueVoteRepository uniqueVoteRepository,
                       ParticipantGroupsRepository participantGroupsRepository,
                       KafkaTemplate<String, String> kafkaTemplate) {
        this.repeatedVoteRepository = repeatedVoteRepository;
        this.uniqueVoteRepository = uniqueVoteRepository;
        this.participantGroupsRepository = participantGroupsRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void storeVote(VoteEvent vote, Instant createdAt) {

        Optional<ParticipantGroups> part = participantGroupsRepository.findById(vote.groupId());
        if(part.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        Optional<UniqueVote> search = uniqueVoteRepository
                .findByUserEmailAndGroupId(vote.userEmail(), vote.groupId());

        if(search.isPresent()){
            Optional<RepeatedVote> repeatedVote  = repeatedVoteRepository.findByUserEmail(vote.userEmail());
            RepeatedVote repeated;
            if(repeatedVote.isPresent()) {
                repeated = repeatedVote.get();
                repeated.getVotes().add(new RepeatedVote.Vote(createdAt, vote.groupId()));
            }else{
                repeated = new RepeatedVote(List.of(new RepeatedVote.Vote(createdAt, vote.groupId()))
                        , vote.userEmail());
            }
            repeatedVoteRepository.save(repeated);
        }else{
            kafkaTemplate.send("unique-votes", vote.groupId());
            uniqueVoteRepository.save(new UniqueVote(vote.userEmail(), vote.groupId(),
                    createdAt));
        }
    }
}
