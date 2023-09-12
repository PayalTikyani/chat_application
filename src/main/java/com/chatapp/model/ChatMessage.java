package com.chatapp.model;

import java.util.Date;

public class ChatMessage {
	
	private String id;
	private String chatId;
	private String senderId;
	private String sendarName;
	private String recipientId;
	private String recipientName;
	private String content;
	private MessageStatus status;
	private Date timeStamp;
	
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String id, String chatId, String senderId, String sendarName, String recipientId,
			String recipientName, String content, MessageStatus status, Date timeStamp) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.senderId = senderId;
		this.sendarName = sendarName;
		this.recipientId = recipientId;
		this.recipientName = recipientName;
		this.content = content;
		this.status = status;
		this.timeStamp = timeStamp;
	}

	public enum MessageStatus{
		RECEIVED, DELIVERED
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

	public String getSendarName() {
		return sendarName;
	}

	public void setSendarName(String sendarName) {
		this.sendarName = sendarName;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
