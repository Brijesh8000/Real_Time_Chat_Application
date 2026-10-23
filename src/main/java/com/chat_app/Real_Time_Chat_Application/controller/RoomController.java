package com.chat_app.Real_Time_Chat_Application.controller;



import com.chat_app.Real_Time_Chat_Application.repositry.RoomRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chat_app.Real_Time_Chat_Application.entity.Message;
import com.chat_app.Real_Time_Chat_Application.entity.Room;
import com.chat_app.Real_Time_Chat_Application.service.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

	@Autowired
	private RoomService service;

	

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestParam String roomName) {
        Room room = service.findRoom(roomName);

        if (room != null) {
            return ResponseEntity.badRequest().body("Room already exist");
        } else {
           Room room1= service.createsRoom(roomName);
           return ResponseEntity.ok(room1);
            
        }
    }
    
    @GetMapping("/{roomName}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomName){
    	
    	Room room =service.findRoom(roomName);
    	if(room==null) {
    		return ResponseEntity.badRequest().body("Room Not Found");
    	}
    	
    	return ResponseEntity.ok(room);
    }
    
    @GetMapping("/{roomName}/messages")
    public ResponseEntity<List<Message>> getMessages(
    		@PathVariable String roomName 
//    		 @RequestParam(value = "page", defaultValue = "0", required = false) int page,
//             @RequestParam(value = "size", defaultValue = "20", required = false) int size
    		){
    	
    	Room room =service.findRoom(roomName);
    	if(room ==null) {
    		return ResponseEntity.badRequest().build();    
    		}
    	else {
    		
    		
    		List<Message> messages = room.getMessages();
//            int start = Math.max(0, messages.size() - (page + 1) * size);
//            int end = Math.min(messages.size(), start + size);
//            List<Message> paginatedMessages = messages.subList(start, end);
            return ResponseEntity.ok(messages);

    	}
    	
    }
    
    
    
    
    
    
    
}
