package org.example.voting.api.dto;

import org.example.voting.api.model.ConfigParameters;

public record ConfigDTO(String key, String value, ConfigParameters.Type type) {

    public static ConfigDTO from(ConfigParameters config){
        return new ConfigDTO(config.getKey(), (String) config.getValue(), config.getType());
    }
}
