package com.example.gameengineservice.controller;

import com.example.gameengineservice.dto.ChatMessage;
import com.example.gameengineservice.dto.GameEvent;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class GameSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public GameSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/{gameId}")
    public void handleChat(@DestinationVariable String gameId, ChatMessage incomingMessage) {

        GameEvent event = new GameEvent(
                "CHAT",
                gameId,
                null,
                incomingMessage.sender() + " dit : " + incomingMessage.content(),
                LocalDateTime.now().toString()
        );

        messagingTemplate.convertAndSend("/topic/game/" + gameId, event);
    }
}