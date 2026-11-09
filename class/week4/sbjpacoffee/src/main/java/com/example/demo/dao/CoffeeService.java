package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeRepository;

import java.util.*;

@Service
public class CoffeeService {
	@Autowired
	CoffeeRepository repo;	
	
   public List<Coffee> getAll(){
	   return repo.findAll();
   }
}
