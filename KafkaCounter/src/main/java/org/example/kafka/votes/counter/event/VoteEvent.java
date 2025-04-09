package org.example.kafka.votes.counter.event;

public record VoteEvent(String userEmail, String setId) {
}
