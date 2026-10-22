package com.chat_app.Real_Time_Chat_Application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.Real_Time_Chat_Application.entity.Room;
import com.chat_app.Real_Time_Chat_Application.repositry.RoomRepository;

import java.time.LocalDateTime;
import java.util.*;

import javax.management.RuntimeErrorException;

import com.chat_app.Real_Time_Chat_Application.dto.MessageRequestDTO;
import com.chat_app.Real_Time_Chat_Application.entity.Message;

@Service
public class RoomService {
	
	private final RoomRepository roomRepo;

	RoomService(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
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
	
	public Message addMessage(MessageRequestDTO request) {
		Room room=roomRepo.findByRoomName(request.getRoomName());
		
		Message message =new Message();
		message.setMessage(request.getContent());
		message.setSender(request.getSender());
		message.setTimestamp(LocalDateTime.now());
		if(room!=null) {
			room.getMessages().add(message);
			roomRepo.save(room);
		}
		else {
			throw new RuntimeException("Room Not Found");
		}
		
		return message;
		
	}
	

}
