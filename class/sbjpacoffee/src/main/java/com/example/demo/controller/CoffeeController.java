package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CoffeeService;
import com.example.demo.model.Coffee;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {
	@Autowired
	CoffeeService cofs;
	
	@GetMapping
	public ResponseEntity <List<Coffee>> getAll()
	{
		List<Coffee> list = cofs.getAll();
		
		if(list != null && list.size() > 0)
		{
			return ResponseEntity.ok(list);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
}
