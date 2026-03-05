package com.example.gameengineservice.client;

import com.example.gameengineservice.dto.PlayerDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PlayerClient {

    private final RestClient restClient;

    public PlayerClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081/api/players")
                .build();
    }

    // récupérer un joueur
    public PlayerDTO getPlayerById(Long id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .body(PlayerDTO.class);
    }

    // ajouter des points au joueur
    public void updatePlayerScore(Long playerId, int scoreToAdd) {
        restClient.patch()
                .uri("/{id}/score", playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ScorePatchRequest(scoreToAdd))
                .retrieve()
                .toBodilessEntity();
    }

    // DTO utilisé pour le PATCH
    public record ScorePatchRequest(int scoreToAdd) {}
}