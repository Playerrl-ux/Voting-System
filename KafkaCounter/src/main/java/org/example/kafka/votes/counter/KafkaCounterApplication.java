package org.example.kafka.votes.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaCounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaCounterApplication.class, args);
    }

}
