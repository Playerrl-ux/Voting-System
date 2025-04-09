package org.example.voting.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ConfigParameters {

    @Id
    private String key;
    private Object value;
    private Type type;

    public enum Type{
        Integer,
        String,
        ListString
    }
}
