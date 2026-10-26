package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SupplierService;
import com.example.demo.model.Supplier;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService srv;
	
	@GetMapping
	public ResponseEntity<List<Supplier>> getAll() {
		
		return ResponseEntity.ok(srv.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Supplier> create(@RequestBody Supplier sup){
		Supplier rt = srv.create(sup);
		if(rt != null)
			return ResponseEntity.ok(rt);
		else 
		{
			return ResponseEntity.noContent().build();
		}
	}

}
