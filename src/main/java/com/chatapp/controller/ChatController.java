package com.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chatapp.model.ChatMessage;
import com.chatapp.service.ChatMessageService;
import com.chatapp.service.ChatRoomService;

@Controller
public class ChatController {
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chat")
	public void processMessage(@Payload ChatMessage chatMessage) {
		var chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
		chatMessage.setChatId(chatId.get());
		
		ChatMessage saved = chatMessageService.save(chatMessage);
		simpMessagingTemplate.convertAndSendToUser(saved.getRecipientId(), "/queue/messages", saved.getContent());
	}
	
	@GetMapping("/messages/{senderId}/{receiverId}/count")
	public ResponseEntity<Long> countNewMessages(@PathVariable String senderId, @PathVariable String receiverId){
		return ResponseEntity.ok(chatMessageService.countNewMessage(senderId, receiverId));
	}
	
	@GetMapping("/messages/{senderId}/{receiverId}")
	public ResponseEntity<?> findChatMessages(@PathVariable String senderId, @PathVariable String receiverId){
		return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
	}
	
	@GetMapping("/messages/{id}")
	public ResponseEntity<?> findMessages(@PathVariable String id){
		return ResponseEntity.ok(chatMessageService.findById(id));
	}

}
