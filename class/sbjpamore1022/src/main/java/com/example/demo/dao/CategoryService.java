package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;
	
	public List<Category> getAll()
	{
		return repo.findAll();
	}
	
	
	public List<Category> getSqlAll()
	{
		return repo.findCategoriesWithProductsRaw();
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
