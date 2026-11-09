package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
    EmailService emailService;
    
    public void placeOrder() {
    	   emailService.send("Order Send...");
    }
}
