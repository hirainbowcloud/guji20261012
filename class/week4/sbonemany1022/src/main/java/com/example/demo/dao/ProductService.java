package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import java.util.*;

@Service
public class ProductService {
	
//	final ProductRepository repo;
//	public ProductService(ProductRepository repo) {
//		this.repo=repo;
//	}
	@Autowired
	ProductRepository repo;
	
	public List<Product> getAll(){
		return repo.findAll();
	}
	
	public List<Product> findByNameContain(String n){
		return repo.findByNameContaining(n);
	}
	
	public List<Product> findCategoryByPriceDesc(String category){
		return repo.findByCategoryOrderByPriceDesc(category);
	}
	
	public List<Product> findAvailableByCategory(String category){
		return repo.findAvailableByCategory(category);
	}
	
	public List<Product> findMysqlAvailableByCategory(String category){
		return repo.findMysqlAvailableByCategory(category);
	}
}
