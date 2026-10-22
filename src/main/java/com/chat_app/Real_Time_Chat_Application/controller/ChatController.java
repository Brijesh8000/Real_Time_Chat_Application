package com.chat_app.Real_Time_Chat_Application.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat_app.Real_Time_Chat_Application.dto.MessageRequestDTO;
import com.chat_app.Real_Time_Chat_Application.entity.Message;
import com.chat_app.Real_Time_Chat_Application.entity.Room;
import com.chat_app.Real_Time_Chat_Application.service.RoomService;

@Controller
public class ChatController {
	@Autowired
	private RoomService service;
	
	
	@MessageMapping("sendMessage/{roomName}")
	@SendTo("/topic/room/{roomName}")
	public Message sendMessage(
		@DestinationVariable	String roomName,
		@RequestBody	MessageRequestDTO request
			) {
		
	 Message messsage=service.addMessage(request);
		
//		Message massage=new Message();
//		massage.setMessage(request.getContent());
//		massage.setSender(request.getSender());
//		massage.setTimestamp(LocalDateTime.now());
		
		return null;
		
	}

}
