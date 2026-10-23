package com.chat_app.Real_Time_Chat_Application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.Real_Time_Chat_Application.dto.MessageRequestDTO;
import com.chat_app.Real_Time_Chat_Application.entity.Message;
import com.chat_app.Real_Time_Chat_Application.entity.Room;
import com.chat_app.Real_Time_Chat_Application.repositry.MessageRepository;
import com.chat_app.Real_Time_Chat_Application.repositry.RoomRepository;

@Service
public class MessageService {
	
	private MessageRepository messageRepo;
	private RoomRepository roomRepo;
	
	public MessageService(MessageRepository messageRepo,RoomRepository roomRepo)
	{
		this.messageRepo=messageRepo;
		this.roomRepo=roomRepo;
	}
	public Message addMessage(String roomName,MessageRequestDTO request) {
		Room room=roomRepo.findByRoomName(roomName);
		
		Message message = new Message();
	    message.setSender(request.getSender().trim());
	    message.setMessage(request.getContent().trim());
	    message.setTimestamp(LocalDateTime.now());
	    
		
		if(room!=null) {
//			room.getMessages().add(message);
			message.setRoom(room);
			return messageRepo.save(message);
		}
		else {
			throw new RuntimeException("Room Not Found");
			}
		
	}
	

}
