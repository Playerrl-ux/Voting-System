package org.example.kafkastream.votes.controller;

import org.example.kafkastream.votes.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/results")
    public ResponseEntity<Map<String, Double>> getResults() {
        return ResponseEntity.ok(resultService.getResults());
    }
}
