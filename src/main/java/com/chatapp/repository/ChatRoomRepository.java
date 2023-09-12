package com.chatapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.model.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>{
	Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String receiverId);

}
