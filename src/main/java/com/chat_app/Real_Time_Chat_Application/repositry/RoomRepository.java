package com.chat_app.Real_Time_Chat_Application.repositry;

import java.util.List;
import com.chat_app.Real_Time_Chat_Application.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat_app.Real_Time_Chat_Application.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{
	public Room findByRoomName(String room);
	
	public List<Message> findMessageByRoomName(String roomName);

	public Message save(Message message);

}
