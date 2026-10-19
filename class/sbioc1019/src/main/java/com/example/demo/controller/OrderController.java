package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderMessage;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderService order;
	
	@GetMapping("/message/{msg}")
	public OrderMessage sendMessage(@PathVariable String msg)
	{
		order.placeOrder();
		OrderMessage msgObj = new OrderMessage(msg);
		return msgObj;		
	}
}
