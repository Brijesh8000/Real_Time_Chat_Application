package com.chat_app.Real_Time_Chat_Application.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat_app.Real_Time_Chat_Application.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}