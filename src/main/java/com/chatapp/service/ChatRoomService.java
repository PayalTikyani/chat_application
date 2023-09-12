package com.chatapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.model.ChatRoom;
import com.chatapp.repository.ChatRoomRepository;

@Service
public class ChatRoomService {
	
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	public Optional<String> getChatId(String senderId, String receiverId, boolean createIfNotExist){
		Optional<ChatRoom> chatRoom = chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId);
		if(chatRoom.isPresent()) {
			return Optional.of(chatRoom.get().getChatId());
		}
		
		if(!createIfNotExist) {
			return null;
		}
		
		String chatId = String.format("%s_%s", senderId, receiverId);
		
		ChatRoom senderReceiverRoom = new ChatRoom(chatId, senderId, receiverId);
		ChatRoom receiverSenderRoom = new ChatRoom(chatId, receiverId, senderId);
		
		chatRoomRepository.save(senderReceiverRoom);
		chatRoomRepository.save(receiverSenderRoom);
		
		return Optional.of(chatId);
	}
}
