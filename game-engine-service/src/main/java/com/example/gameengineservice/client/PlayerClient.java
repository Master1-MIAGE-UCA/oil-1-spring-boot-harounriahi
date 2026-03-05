package com.example.gameengineservice.client;

import com.example.gameengineservice.dto.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PlayerClient {

    private final RestClient.Builder builder;
    private final DiscoveryClient discoveryClient;

    private RestClient playerRestClient() {
        String baseUrl = discoveryClient.getServiceUrl("player-service");
        return builder.baseUrl(baseUrl + "/api/players").build();
    }

    public PlayerDTO getPlayerById(Long id) {
        return playerRestClient()
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(PlayerDTO.class);
    }

    public void updatePlayerScore(Long playerId, int scoreToAdd) {
        playerRestClient()
                .patch()
                .uri("/{id}/score", playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ScorePatchRequest(scoreToAdd))
                .retrieve()
                .toBodilessEntity();
    }

    public record ScorePatchRequest(int scoreToAdd) {}
}