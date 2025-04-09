package org.example.voting.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@Data
@Document
public class Participant {

    @Id
    private String id;
    private String name;
    private Type type;
    private short yearOfParticipation;

    public Participant(String name, Type type, short year) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.yearOfParticipation = year;
    }

    public enum Type{
        PIPOCA,
        CAMAROTE
    }
}
