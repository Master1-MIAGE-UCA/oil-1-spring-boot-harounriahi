package com.example.playerservice.controller;

import com.example.playerservice.entity.Player;
import com.example.playerservice.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/players") // URI de base
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findPlayerById(id));
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {

        // Sécurité : Assurer que la BDD génère l'ID
        player.setId(null);

        Player savedPlayer = playerService.savePlayer(player);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlayer.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> partialUpdatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.partialUpdatePlayer(id, player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
