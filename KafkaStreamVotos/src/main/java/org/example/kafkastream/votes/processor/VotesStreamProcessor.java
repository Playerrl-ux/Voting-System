package org.example.kafkastream.votes.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Component;

@Component
public class VotesStreamProcessor {

    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> validVotes = builder.stream("unique-votes");


        validVotes.foreach((key, value) -> {
            System.out.println("Received vote for ID: " + value);
        });


        KStream<String, String> rekeyedVotes = validVotes
                .selectKey((ignoredKey, voteId) -> voteId);

        rekeyedVotes.foreach((key, value) -> {System.out.println("Received rekeyed vote for ID: " + value);});

        KTable<String, Long> votesPerId = rekeyedVotes
                .groupByKey()
                .count(Materialized.as("votes-per-id"));

        KTable<String, Long> totalVotes = votesPerId
                .toStream()
                .groupBy((id, count) -> "total", Grouped.with(Serdes.String(), Serdes.Long()))
                .reduce(Long::sum, Materialized.as("total-votes"));

        return builder.build();
    }
}
