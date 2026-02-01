package com.example.playerservice.service;

import com.example.playerservice.entity.Player;
import com.example.playerservice.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAllPlayers() {
        return repository.findAll();
    }

    public Player findPlayerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Player createPlayer(Player player) {
        return repository.save(player);
    }
}
