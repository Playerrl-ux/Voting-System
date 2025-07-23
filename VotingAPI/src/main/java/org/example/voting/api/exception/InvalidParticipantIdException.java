package org.example.voting.api.exception;

public class InvalidParticipantIdException extends RuntimeException{

    private final String id;

    public InvalidParticipantIdException(String id) {
        super("Invalid participant id: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
