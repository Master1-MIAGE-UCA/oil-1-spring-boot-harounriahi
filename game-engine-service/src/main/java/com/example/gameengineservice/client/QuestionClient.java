package com.example.gameengineservice.client;

import com.example.gameengineservice.dto.QuestionDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class QuestionClient {

    private final RestClient restClient;

    public QuestionClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8082/api/questions")
                .build();
    }

    public List<QuestionDTO> getAllQuestions() {
        return restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<QuestionDTO>>() {});
    }
}