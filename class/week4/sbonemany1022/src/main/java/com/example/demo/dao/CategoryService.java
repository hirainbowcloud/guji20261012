package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import java.util.*;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository repo;
	
	public List<Category> getAll(){
		return repo.findAll();
	}
	
	public Category save(Category c) {
		if (c.getProducts() != null) {
			for (Product p : c.getProducts()) {
				p.setCategory(c);
			}
		}
		return repo.save(c);
	}
}
