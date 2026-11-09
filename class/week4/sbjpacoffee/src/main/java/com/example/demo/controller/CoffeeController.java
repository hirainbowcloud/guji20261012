package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CoffeeService;
import com.example.demo.model.Coffee;

import java.util.*;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
	@Autowired
	CoffeeService srv;
	
	@GetMapping
	public ResponseEntity<List<Coffee>>  getAll(){
		return ResponseEntity.ok(srv.getAll());
	}

}
