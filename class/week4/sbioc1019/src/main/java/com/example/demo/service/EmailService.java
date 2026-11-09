package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EmailService implements NotificationService{

	@Override
	public void send(String message) {
		// TODO Auto-generated method stub
		System.out.println("發送郵件: " + message);
	}

}
