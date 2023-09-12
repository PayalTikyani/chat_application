package com.chatapp.model;

public class ChatRoom {
	private String id;
	private String chatId;
	private String senderId;
	private String receiverId;
	
	public ChatRoom() {
		
	}
	
	public ChatRoom(String chatId, String senderId, String receiverId) {
		super();
		this.chatId = chatId;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	

}
