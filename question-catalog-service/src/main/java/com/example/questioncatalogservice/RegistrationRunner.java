package com.example.questioncatalogservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class RegistrationRunner implements CommandLineRunner {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String appName;

    private final RestClient restClient = RestClient.create();

    @Override
    public void run(String... args) {
        String myUrl = "http://localhost:" + port;

        Map<String, String> body = Map.of(
                "serviceName", appName,
                "url", myUrl
        );

        restClient.post()
                .uri("http://localhost:8761/api/registry")
                .body(body)
                .retrieve()
                .toBodilessEntity();

        System.out.println("✅ Auto-registered: " + appName + " -> " + myUrl);
    }
}