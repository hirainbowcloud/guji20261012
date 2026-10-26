package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository dao;
	
	public List<Product> findByNameContain(String keyword)
	{
		return dao.findByNameContaining(keyword);
	}
	
	public List<Product> findByCategoryOrderByPriceDesc (String keyword)
	{
		return dao.findByCategoryOrderByPriceDesc(keyword);
	}
	
	public List<Product> findAll()
	{
		return dao.findAll();
	}
	
	public Product createProduct (Product prd)
	{
		try 
		{
			Product list = dao.save(prd);
			return list;
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	public List<Product> findAvailableByCategory(String category){
		return dao.findAvailableByCategory(category);
	}
	
	public List<Product> findMysqlAvailableByCategory(String category){
		return dao.findMysqlAvailableByCategory(category);
	}
	
	//分頁查詢
	public Page<Product> findPaged(int page, int size, String sortBy)
	{
		return dao.findAll(
				PageRequest.of(page, size,Sort.by(sortBy).descending())
				);
	}
}
