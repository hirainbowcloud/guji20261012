package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")

public class HelloController 
{
	@GetMapping
	@ResponseBody
	public String sayHello()
	{
		return "Bye Bye";
	}
}

