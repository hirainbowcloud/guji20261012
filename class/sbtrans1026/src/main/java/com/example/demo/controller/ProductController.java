package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductService;
import com.example.demo.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	ProductService service;

	public ProductController(ProductService srv) {
		this.service = srv;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/order/{pid}/{qty}")
	public ResponseEntity<Integer> orderStock(@PathVariable long pid, @PathVariable int qty) {
		try {
			//service.deductStock(pid, qty); //不會扣掉數量，有執行Transactional
			service.placeOrder(pid, qty); //會扣掉數量
		} catch (Exception ex) {
			System.out.println("Stock Exception =>" + pid + " :" + ex.getMessage());
		}
		int stock = service.getStockById(pid);
		return ResponseEntity.ok(Integer.valueOf(stock));
	}
	
	 @GetMapping("/price/{pid}/{price}")
	   public ResponseEntity<Product> updatePrice(@PathVariable long pid ,@PathVariable double price){
		   try {
		     //service.deductStock(pid, qty);
			   service.updatePrice(pid, price);
		   }catch(Exception ex) {
			   System.out.println("Price Exception =>"+ pid+ " :"+ex.getMessage());
		   }	   
		   return ResponseEntity.ok(service.findById(pid));
	   }
}
