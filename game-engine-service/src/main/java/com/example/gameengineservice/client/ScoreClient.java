package com.example.gameengineservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ScoreClient {

    private final RestClient restClient;

    public ScoreClient(@Value("${score.service.url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public void sendScore(Long playerId, int score) {
        restClient.post()
                .uri("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ScoreRequest(playerId, score))
                .retrieve()
                .toBodilessEntity();
    }

    public record ScoreRequest(Long playerId, int score) {}
}