package com.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.chatapp.exception.ResourceNotFoundException;
import com.chatapp.model.ChatMessage;
import com.chatapp.model.ChatMessage.MessageStatus;
import com.chatapp.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public ChatMessage save(ChatMessage chatMessage) {
		chatMessage.setStatus(ChatMessage.MessageStatus.RECEIVED);
		chatMessageRepository.save(chatMessage);
		return chatMessage;
	}
	
	public long countNewMessage(String senderId, String receiverId) {
		return chatMessageRepository.countBySenderIdAndReceiverIdAndStatus(senderId, receiverId, MessageStatus.RECEIVED);
	}
	
	public List<ChatMessage> findChatMessages(String senderId, String receiverId){
		var chatId = chatRoomService.getChatId(senderId, receiverId, false);
		
		var messages = chatId.map(cId -> chatMessageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, receiverId, ChatMessage.MessageStatus.DELIVERED);
        }

        return messages;
	}
	
	public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        Query query = new Query(
                Criteria
                        .where("senderId").is(senderId)
                        .and("recipientId").is(recipientId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }
	
	 public ChatMessage findById(String id) {
	        return chatMessageRepository
	                .findById(id)
	                .map(chatMessage -> {
	                    chatMessage.setStatus(MessageStatus.DELIVERED);
	                    return chatMessageRepository.save(chatMessage);
	                })
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("can't find message (" + id + ")"));
	    }
	
}
