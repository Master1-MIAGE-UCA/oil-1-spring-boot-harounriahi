package com.example.scoreservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime playedAt; // Date de la partie
    private Long playerId;          // ID du joueur concerné
    private int score;              // Score obtenu durant cette partie
}