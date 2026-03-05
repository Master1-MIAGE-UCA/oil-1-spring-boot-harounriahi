package com.example.gameengineservice.service;

import com.example.gameengineservice.client.PlayerClient;
import com.example.gameengineservice.client.QuestionClient;
import com.example.gameengineservice.client.ScoreClient;
import com.example.gameengineservice.dto.GameDTO;
import com.example.gameengineservice.dto.PlayerDTO;
import com.example.gameengineservice.dto.QuestionDTO;
import com.example.gameengineservice.dto.ScoreDTO;
import com.example.gameengineservice.dto.GameEvent;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class GameService {

    private final PlayerClient playerClient;
    private final QuestionClient questionClient;
    private final ScoreClient scoreClient;
    private final SimpMessagingTemplate messagingTemplate;

    public GameService(PlayerClient playerClient,
                       QuestionClient questionClient,
                       ScoreClient scoreClient,
                       SimpMessagingTemplate messagingTemplate) {

        this.playerClient = playerClient;
        this.questionClient = questionClient;
        this.scoreClient = scoreClient;
        this.messagingTemplate = messagingTemplate;
    }

    public GameDTO startNewGame(Long playerId, int nbQuestions) {

        // récupérer le joueur
        PlayerDTO player = playerClient.getPlayerById(playerId);

        // récupérer N questions aléatoires
        List<QuestionDTO> selected = IntStream.range(0, nbQuestions)
                .mapToObj(i -> questionClient.getRandomQuestion())
                .toList();

        return new GameDTO(UUID.randomUUID().toString(), player, selected);
    }

    /**
     * Fin de partie
     */
    public void endGame(Long playerId, int score) {

        scoreClient.saveScore(new ScoreDTO(playerId, score));

        playerClient.updatePlayerScore(playerId, score);
    }

    /**
     * Envoi d'un événement dans une ROOM
     */
    public void notifyGameUpdate(String gameId, GameEvent event) {

        String destination = "/topic/game/" + gameId;

        messagingTemplate.convertAndSend(destination, event);
    }

    /**
     * Message privé à un joueur
     */
    public void sendPrivateMessage(Long playerId, String secretInfo) {

        String destination = "/topic/player/" + playerId;

        GameEvent event = new GameEvent(
                "SECRET",
                null,
                playerId,
                secretInfo,
                LocalDateTime.now().toString()
        );

        messagingTemplate.convertAndSend(destination, event);
    }
}