package com.example.gameengineservice.client;

import com.example.gameengineservice.dto.ScoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ScoreClient {

    private final RestClient.Builder builder;
    private final DiscoveryClient discoveryClient;

    private RestClient scoreRestClient() {

        String baseUrl = discoveryClient.getServiceUrl("score-service");

        return builder
                .baseUrl(baseUrl + "/api/scores")
                .build();
    }

    public void saveScore(ScoreDTO scoreDTO) {

        scoreRestClient()
                .post()
                .uri("/")
                .body(scoreDTO)
                .retrieve()
                .toBodilessEntity();
    }
}