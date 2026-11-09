package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Order2Service {
	 
	EmailService emailService;
	 
	 public Order2Service(EmailService srv) {
		 this.emailService=srv;
	 }
	 public void sendEmail() {
	        emailService.send("Order2 Service Hello");
	 }
}
