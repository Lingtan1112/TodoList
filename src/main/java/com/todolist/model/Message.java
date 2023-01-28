package com.todolist.model;

import org.springframework.http.HttpStatus;

public class Message {
	
	public Message(String message, HttpStatus badRequest) {
		this.setMessage(message);
		this.setResponseCode(badRequest);
	}

	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus badRequest) {
		this.responseCode = badRequest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	private HttpStatus responseCode;
}
