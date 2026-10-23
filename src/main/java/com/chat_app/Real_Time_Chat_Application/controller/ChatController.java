package com.chat_app.Real_Time_Chat_Application.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat_app.Real_Time_Chat_Application.dto.MessageRequestDTO;
import com.chat_app.Real_Time_Chat_Application.entity.Message;
import com.chat_app.Real_Time_Chat_Application.service.MessageService;
import com.chat_app.Real_Time_Chat_Application.service.RoomService;

@Controller
public class ChatController {

    private final MessageService service;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(
            MessageService service,
            SimpMessagingTemplate messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage/{roomName}")
    public void sendMessage(
            @DestinationVariable String roomName,
            @Payload MessageRequestDTO request) {

        

        Message savedMessage =service.addMessage(roomName, request);

        
        // Broadcast only after the service successfully saves the message.
        messagingTemplate.convertAndSend(
            "/topic/room/" + roomName,
            savedMessage
        );
    }
}