package org.example.voting.api.dto;

import org.example.voting.api.model.User;

public record UserDTO(String name, String email) {

    public static UserDTO from(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }
}
