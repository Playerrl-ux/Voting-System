package org.example.voting.api.handler;

import org.example.voting.api.exception.InvalidParticipantIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GroupExceptionHandler {

    @ExceptionHandler(InvalidParticipantIdException.class)
    public ResponseEntity<Map<String, String>> handleInvalidParticipantIdException(InvalidParticipantIdException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}
