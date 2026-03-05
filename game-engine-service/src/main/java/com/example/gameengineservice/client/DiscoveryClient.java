package com.example.gameengineservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Random;

@Component
public class DiscoveryClient {

    private final RestClient discovery = RestClient.create("http://localhost:8761");
    private final Random random = new Random();

    public String getServiceUrl(String serviceName) {

        List<String> urls = discovery.get()
                .uri("/api/discovery/{serviceName}", serviceName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<String>>() {});

        if (urls == null || urls.isEmpty()) {
            throw new RuntimeException("Service not found: " + serviceName);
        }

        String chosen = urls.get(random.nextInt(urls.size()));
        System.out.println("🎯 DISCOVERY choose " + serviceName + " -> " + chosen);

        return chosen;
    }
}