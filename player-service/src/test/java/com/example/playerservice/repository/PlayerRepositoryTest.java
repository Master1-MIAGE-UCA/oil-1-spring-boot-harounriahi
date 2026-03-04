package com.example.playerservice.repository;

import com.example.playerservice.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldFindPlayerByPseudo() {
        // pseudo unique pour éviter conflit avec les joueurs seed
        String pseudo = "Trinity_test_" + System.nanoTime();

        Player player = Player.builder()
                .pseudo(pseudo)
                .score(10)
                .build();

        playerRepository.save(player);

        Optional<Player> found = playerRepository.findByPseudo(pseudo);

        assertTrue(found.isPresent());
        assertEquals(10, found.get().getScore());
    }

    @Test
    void shouldSavePlayer() {
        // pseudo unique pour éviter conflit avec les joueurs seed
        String pseudo = "Morpheus_test_" + System.nanoTime();

        Player player = Player.builder()
                .pseudo(pseudo)
                .score(0)
                .build();

        Player saved = playerRepository.save(player);

        assertNotNull(saved.getId());
        assertEquals(pseudo, saved.getPseudo());
    }

    @Test
    void shouldReturnEmptyWhenPseudoNotFound() {
        Optional<Player> found = playerRepository.findByPseudo("Unknown_" + System.nanoTime());
        assertTrue(found.isEmpty());
    }
    @Test
    void shouldDeletePlayerById() {
        String pseudo = "Bob_test_" + System.nanoTime();
        Player saved = playerRepository.save(Player.builder().pseudo(pseudo).score(1).build());

        playerRepository.deleteById(saved.getId());

        assertTrue(playerRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    void shouldUpdatePlayerScore() {
        String pseudo = "Eve_test_" + System.nanoTime();
        Player saved = playerRepository.save(Player.builder().pseudo(pseudo).score(5).build());

        saved.setScore(42);
        playerRepository.save(saved);

        Player reloaded = playerRepository.findById(saved.getId()).orElseThrow();
        assertEquals(42, reloaded.getScore());
    }
}