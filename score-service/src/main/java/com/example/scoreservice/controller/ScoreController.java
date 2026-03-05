package com.example.scoreservice.controller;

import com.example.scoreservice.dto.ScoreRequest;
import com.example.scoreservice.entity.GameHistory;
import com.example.scoreservice.service.GameHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final GameHistoryService service;

    @PostMapping
    public GameHistory saveScore(@RequestBody ScoreRequest request) {
        return service.saveGame(request);
    }
}