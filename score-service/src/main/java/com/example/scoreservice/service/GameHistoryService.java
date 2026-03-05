package com.example.scoreservice.service;

import com.example.scoreservice.dto.ScoreRequest;
import com.example.scoreservice.entity.GameHistory;
import com.example.scoreservice.repository.GameHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameHistoryService {

    private final GameHistoryRepository repository;

    public GameHistory saveGame(ScoreRequest req) {
        GameHistory gh = GameHistory.builder()
                .playerId(req.playerId())
                .score(req.score())
                .playedAt(LocalDateTime.now())
                .build();

        return repository.save(gh);
    }
}