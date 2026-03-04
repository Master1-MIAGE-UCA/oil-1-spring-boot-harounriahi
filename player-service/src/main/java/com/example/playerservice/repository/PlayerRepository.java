package com.example.playerservice.repository;

import com.example.playerservice.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPseudo(String pseudo);
}