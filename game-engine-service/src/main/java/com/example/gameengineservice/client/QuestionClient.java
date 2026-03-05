package com.example.gameengineservice.client;

import com.example.gameengineservice.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class QuestionClient {

    private final RestClient.Builder builder;
    private final DiscoveryClient discoveryClient;

    private RestClient questionRestClient() {

        String baseUrl = discoveryClient.getServiceUrl("question-catalog-service");

        return builder
                .baseUrl(baseUrl + "/api/questions")
                .build();
    }

    public QuestionDTO getRandomQuestion() {

        return questionRestClient()
                .get()
                .uri("/random")
                .retrieve()
                .body(QuestionDTO.class);
    }
}