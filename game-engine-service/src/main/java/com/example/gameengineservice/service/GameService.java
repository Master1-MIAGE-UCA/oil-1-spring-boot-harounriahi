package com.example.gameengineservice.service;

import com.example.gameengineservice.client.PlayerClient;
import com.example.gameengineservice.client.QuestionClient;
import com.example.gameengineservice.client.ScoreClient;
import com.example.gameengineservice.dto.GameDTO;
import com.example.gameengineservice.dto.PlayerDTO;
import com.example.gameengineservice.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final PlayerClient playerClient;
    private final QuestionClient questionClient;
    private final ScoreClient scoreClient;

    public GameService(PlayerClient playerClient, QuestionClient questionClient, ScoreClient scoreClient) {
        this.playerClient = playerClient;
        this.questionClient = questionClient;
        this.scoreClient = scoreClient;
    }

    public GameDTO startNewGame(Long playerId, int nbQuestions) {

        // 1) récupérer le joueur depuis player-service
        PlayerDTO player = playerClient.getPlayerById(playerId);

        // 2) récupérer toutes les questions depuis question-service
        List<QuestionDTO> questions = questionClient.getAllQuestions();

        // 3) on prend les N premières
        List<QuestionDTO> selected = questions.stream()
                .limit(nbQuestions)
                .toList();

        // 4) on construit la session
        return new GameDTO(UUID.randomUUID().toString(), player, selected);
    }

    /**
     * Fin de partie : orchestre une "transaction distribuée"
     * 1) archive la session dans score-service
     * 2) ajoute les points au score global du joueur via player-service
     */
    public void endGame(Long playerId, int score) {
        // 1) Archivage
        scoreClient.sendScore(playerId, score);

        // 2) Mise à jour joueur (doit être implémenté dans PlayerClient)
        playerClient.updatePlayerScore(playerId, score);
    }
}