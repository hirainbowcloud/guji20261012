package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ICoffeeRepository;
import com.example.demo.model.Coffee;

@Service
public class CoffeeService {
	
	@Autowired
	ICoffeeRepository repo;
	
	public List<Coffee> getAll()
	{
		return repo.findAll();
	}

}
