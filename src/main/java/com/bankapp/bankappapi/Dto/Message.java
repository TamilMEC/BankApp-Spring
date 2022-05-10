package com.bankapp.bankappapi.Dto;

import lombok.Data;

@Data
public class Message {
	
	private String message;
	
	public Message(String message) {
		this.message=message;
	}
}