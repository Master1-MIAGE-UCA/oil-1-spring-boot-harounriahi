package com.example.gameengineservice.service;

import com.example.gameengineservice.client.PlayerClient;
import com.example.gameengineservice.client.QuestionClient;
import com.example.gameengineservice.dto.GameDTO;
import com.example.gameengineservice.dto.PlayerDTO;
import com.example.gameengineservice.dto.QuestionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock private PlayerClient playerClient;
    @Mock private QuestionClient questionClient;

    @InjectMocks private GameService gameService;

    @Test
    void shouldStartNewGameSuccessfully() {
        Long playerId = 1L;

        PlayerDTO player = new PlayerDTO(playerId, "Neo", 0);
        List<QuestionDTO> questions = List.of(new QuestionDTO(1L, "Q1", "R1"));

        Mockito.when(playerClient.getPlayerById(playerId)).thenReturn(player);
        Mockito.when(questionClient.getAllQuestions()).thenReturn(questions);

        GameDTO result = gameService.startNewGame(playerId, 1);

        assertEquals("Neo", result.player().pseudo());
        assertEquals(1, result.questions().size());
    }
    @Test
    void shouldReturnCorrectPlayerPseudo() {
        Long playerId = 2L;

        PlayerDTO player = new PlayerDTO(playerId, "Trinity", 10);
        List<QuestionDTO> questions = List.of(new QuestionDTO(1L, "Q1", "R1"));

        Mockito.when(playerClient.getPlayerById(playerId)).thenReturn(player);
        Mockito.when(questionClient.getAllQuestions()).thenReturn(questions);

        GameDTO result = gameService.startNewGame(playerId, 1);

        assertEquals("Trinity", result.player().pseudo());
    }

    @Test
    void shouldReturnCorrectNumberOfQuestions() {
        Long playerId = 3L;

        PlayerDTO player = new PlayerDTO(playerId, "Morpheus", 20);
        List<QuestionDTO> questions = List.of(
                new QuestionDTO(1L, "Q1", "R1"),
                new QuestionDTO(2L, "Q2", "R2")
        );

        Mockito.when(playerClient.getPlayerById(playerId)).thenReturn(player);
        Mockito.when(questionClient.getAllQuestions()).thenReturn(questions);

        GameDTO result = gameService.startNewGame(playerId, 2);

        assertEquals(2, result.questions().size());
    }

    @Test
    void shouldCallPlayerClient() {
        Long playerId = 4L;

        PlayerDTO player = new PlayerDTO(playerId, "Smith", 0);
        List<QuestionDTO> questions = List.of(new QuestionDTO(1L, "Q1", "R1"));

        Mockito.when(playerClient.getPlayerById(playerId)).thenReturn(player);
        Mockito.when(questionClient.getAllQuestions()).thenReturn(questions);

        gameService.startNewGame(playerId, 1);

        Mockito.verify(playerClient).getPlayerById(playerId);
    }

    @Test
    void shouldCallQuestionClient() {
        Long playerId = 5L;

        PlayerDTO player = new PlayerDTO(playerId, "Oracle", 0);
        List<QuestionDTO> questions = List.of(new QuestionDTO(1L, "Q1", "R1"));

        Mockito.when(playerClient.getPlayerById(playerId)).thenReturn(player);
        Mockito.when(questionClient.getAllQuestions()).thenReturn(questions);

        gameService.startNewGame(playerId, 1);

        Mockito.verify(questionClient).getAllQuestions();
    }
}