package com.chat_app.Real_Time_Chat_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDTO {
	private String content;
	private String sender;
	private  String roomName;

}
