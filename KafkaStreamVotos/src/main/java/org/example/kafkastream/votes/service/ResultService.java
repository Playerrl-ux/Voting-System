package org.example.kafkastream.votes.service;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResultService {

    private final KafkaStreams kafkaStreams;

    public ResultService(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }


    public Map<String, Double> getResults() {
        ReadOnlyKeyValueStore<String, Long> contagemStore =
                kafkaStreams.store(StoreQueryParameters.fromNameAndType("votes-per-id", QueryableStoreTypes.keyValueStore()));

        Map<String, Double> porcentagens = new HashMap<>();
        KeyValueIterator<String, Long> iterator = contagemStore.all();

        long total = 0;
        Map<String, Long> contagens = new HashMap<>();


        while (iterator.hasNext()) {
            KeyValue<String, Long> entry = iterator.next();
            contagens.put(entry.key, entry.value);
            total += entry.value;
        }
        iterator.close();


        if (total == 0) return Map.of();


        for (Map.Entry<String, Long> entry : contagens.entrySet()) {
            double porcentagem = (entry.getValue() * 100.0) / total;
            porcentagens.put(entry.getKey(), porcentagem);
        }

        return porcentagens;
    }
}