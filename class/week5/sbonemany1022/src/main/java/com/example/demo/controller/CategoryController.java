package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryService;
import com.example.demo.model.*;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService srv;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(srv.getAll());
	}
    
	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody Category c){
		try {		   	
		   return ResponseEntity.ok(srv.save(c));
		}catch(Exception ex) {
			System.out.println("Save category error=>"+ex.getMessage());
		}
		return ResponseEntity.noContent().build();
	}
}
