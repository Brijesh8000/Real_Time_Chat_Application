package com.chat_app.Real_Time_Chat_Application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.Real_Time_Chat_Application.entity.Room;
import com.chat_app.Real_Time_Chat_Application.repositry.MessageRepository;
import com.chat_app.Real_Time_Chat_Application.repositry.RoomRepository;

import java.time.LocalDateTime;
import java.util.*;

import javax.management.RuntimeErrorException;

import com.chat_app.Real_Time_Chat_Application.dto.MessageRequestDTO;
import com.chat_app.Real_Time_Chat_Application.entity.Message;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
	private final RoomRepository roomRepo;
	private final MessageRepository messageRepo;

	public RoomService(
	        RoomRepository roomRepo,
	        MessageRepository messageRepo) {
	    this.roomRepo = roomRepo;
	    this.messageRepo = messageRepo;
	}
	
	public Room findRoom(String roomName) {
		
		Room room=roomRepo.findByRoomName(roomName);
		if(room!=null) {
			return room;
		}
		else {
			return null;
		}
		
		
		
	}
	public Room createsRoom(String roomName) {
		Room room =mapToRoom(roomName);
		roomRepo.save(room);
		return room;
	}
	
	public Room mapToRoom(String roomName) {
		Room room =new Room( roomName);
		return room;
		
	}
	public List<Message> findMessage(String roomName){
		
		List<Message> message=roomRepo.findMessageByRoomName(roomName);
		
		if(message.size()>=0) {
			return message;
			
		}
		else {
			return null;
		}
		

	}
		

}
