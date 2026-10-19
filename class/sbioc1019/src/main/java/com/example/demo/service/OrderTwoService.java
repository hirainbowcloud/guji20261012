package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service // 必須加上，否則 Controller 注入時會報 UnsatisfiedDependencyException
public class OrderTwoService {
	//建構子注入
	
	EmailService emailService;
	
	public OrderTwoService(EmailService srv)
	{
		this.emailService = srv;
	}
	
	public void sendEmail()
	{
		emailService.send("Hi This OrderTwoService...");
	}
}
