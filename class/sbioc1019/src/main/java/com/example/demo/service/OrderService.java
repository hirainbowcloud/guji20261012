package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // 1. 引入 Service 註解

@Service // 2. 加上 @Service，讓 Spring 將它註冊為 Bean
public class OrderService {
	
	@Autowired
	EmailService emailService;
	
	public void placeOrder() {
		emailService.send("Hi! This is OrderService Send...");
	}

}