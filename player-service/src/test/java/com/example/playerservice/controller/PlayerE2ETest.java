package com.example.playerservice.controller;

import com.example.playerservice.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndRetrievePlayer() {

        // pseudo unique pour éviter conflit
        String pseudo = "Alice_test_" + System.nanoTime();

        Player newPlayer = Player.builder()
                .pseudo(pseudo)
                .score(100)
                .build();

        // 1️⃣ POST /api/players
        ResponseEntity<Player> createResponse =
                restTemplate.postForEntity("/api/players", newPlayer, Player.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        Long playerId = createResponse.getBody().getId();

        // 2️⃣ GET /api/players/{id}
        ResponseEntity<Player> getResponse =
                restTemplate.getForEntity("/api/players/" + playerId, Player.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(pseudo, getResponse.getBody().getPseudo());
    }
}