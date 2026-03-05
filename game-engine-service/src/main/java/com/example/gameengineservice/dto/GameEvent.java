package com.example.gameengineservice.dto;

public record GameEvent(
        String type,
        String gameId,
        Long playerId,
        String message,
        String timestamp
) {}