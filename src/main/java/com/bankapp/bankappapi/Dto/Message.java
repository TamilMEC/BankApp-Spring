package com.bankapp.bankappapi.Dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Component
public class Message {
	
	private String message;
	
	public Message(String message) {
		this.message=message;
		}
}