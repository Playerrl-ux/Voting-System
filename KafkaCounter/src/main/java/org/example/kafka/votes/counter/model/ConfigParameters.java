package org.example.kafka.votes.counter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigParameters {

    @Id
    private String key;
    private String value;
    private Type type;

    public enum Type{
        Integer,
        String,
        ListString
    }
}
