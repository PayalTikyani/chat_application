package com.chatapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.model.ChatMessage;
import com.chatapp.model.ChatMessage.MessageStatus;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String>{
	
	long countBySenderIdAndReceiverIdAndStatus(String senderId, String receiverId, MessageStatus messageStatus);
	
	List<ChatMessage> findByChatId(String chatId);

}
