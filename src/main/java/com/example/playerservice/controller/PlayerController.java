package com.example.playerservice.controller;

import com.example.playerservice.entity.Player;
import com.example.playerservice.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return service.findAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return service.findPlayerById(id);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return service.createPlayer(player);
    }
}
