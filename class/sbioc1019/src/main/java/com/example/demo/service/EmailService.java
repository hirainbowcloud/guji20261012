package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EmailService implements INotificationService {

	@Override
	public void send(String message) {
		System.out.println("發送郵件: "+ message);
		
	}

}
