package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderMessage;
import com.example.demo.service.OrderService;
import com.example.demo.service.OrderTwoService;

@RestController
@RequestMapping("/apii")
public class OrderTwoController {
	@Autowired
    OrderService order;
	
	OrderTwoService order2;
	
	public OrderTwoController(OrderTwoService ord) {
		order2=ord;
	}
	
	@GetMapping("/message/{msg}")
	public OrderMessage sendMessage(@PathVariable String msg) {
		order.placeOrder();
		OrderMessage msgObj=new OrderMessage(msg);
		return msgObj;
	}
	@GetMapping("/order2/{msg}")
	public OrderMessage sendOrder2Message(@PathVariable String msg) {
		order2.sendEmail();
		OrderMessage msgObj=new OrderMessage(msg);
		return msgObj;
	}
	
	
}
