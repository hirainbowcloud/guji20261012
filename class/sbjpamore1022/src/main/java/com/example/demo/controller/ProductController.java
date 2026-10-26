package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductService;
import com.example.demo.model.Product;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService srv;
	
	@GetMapping("/name/{keyword}")
	public ResponseEntity getProductName(@PathVariable String keyword)
	{
		return ResponseEntity.ok(srv.findByNameContain(keyword));
	}
	
	@GetMapping("/category/{keyword}")
	public ResponseEntity getCategoryOrderByPriceDesc(@PathVariable String keyword)
	{
		return ResponseEntity.ok(srv.findByCategoryOrderByPriceDesc(keyword));
	}
	
	@GetMapping
	public ResponseEntity getAllProduct()
	{
		List<Product> list = srv.findAll();
		if(list.size() > 0)
		{
			return ResponseEntity.ok(list);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity addProduct(@RequestBody Product prd)
	{
		Product list = srv.createProduct(prd);
		if(list != null)
		{
			return ResponseEntity.ok(list);
		}		
		else
		{
			return ResponseEntity.noContent().build();
		}		
	}
	
	 @GetMapping("/category/price/{ct}")
	 public ResponseEntity<List<Product>> getByCategoryPrice(@PathVariable String ct){
	    	    return ResponseEntity.ok(srv.findAvailableByCategory(ct));
	 }
	 
	 @GetMapping("/category/price/mysql/{ct}")
	 public ResponseEntity<List<Product>> getMysqlByCategoryPrice(@PathVariable String ct){
	    	    return ResponseEntity.ok(srv.findMysqlAvailableByCategory(ct));
	 }
	 
	// GET /api/products/page?page=0&size=5&sortBy=price
	 @GetMapping("/page")
	 public Page<Product> getPage(
			 @RequestParam(defaultValue = "0") int page,
			 @RequestParam(defaultValue = "3") int size,
			 @RequestParam(defaultValue = "price") String sortBy)
			 {
		 		return srv.findPaged(page, size, sortBy);
			 }
			 
}
