package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductService;
import com.example.demo.dto.ProductCreateRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.ProductUpdateRequest;
import com.example.demo.model.Product;

import jakarta.validation.Valid;

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

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest req) {
		Product found = service.findById(id);
		if (found == null) {
			return ResponseEntity.noContent().build();
		}
		found.setName(req.getName());
		found.setPrice(req.getPrice());
		found.setCategory(req.getCategory());
		found.setStock(req.getStock());
		Optional<Product> op = service.update(id, found);
		if (op.isPresent()) {
			ProductResponse res = ProductResponse.from(op.get());
			return ResponseEntity.ok(res);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// POST /api/products — 改用 DTO
	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest req) {
		Product product = new Product(req.getName(), req.getPrice(), req.getStock(), req.getCategory());
		try {
			Product saved = service.create(product);
			URI location = URI.create("/api/products/" + saved.getId());
			return ResponseEntity.created(location).body(ProductResponse.from(saved));
		} catch (Exception ex) {
			System.out.println("Product create error =>" + ex.getMessage());
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/order/{pid}/{qty}")
	public ResponseEntity<Integer> orderStock(@PathVariable long pid, @PathVariable int qty) {
		try {
			// service.deductStock(pid, qty); //不會扣掉數量，有執行Transactional
			service.placeOrder(pid, qty); // 會扣掉數量
		} catch (Exception ex) {
			System.out.println("Stock Exception =>" + pid + " :" + ex.getMessage());
		}
		int stock = service.getStockById(pid);
		return ResponseEntity.ok(Integer.valueOf(stock));
	}

	@GetMapping("/price/{pid}/{price}")
	public ResponseEntity<Product> updatePrice(@PathVariable long pid, @PathVariable double price) {
		try {
			// service.deductStock(pid, qty);
			service.updatePrice(pid, price);
		} catch (Exception ex) {
			System.out.println("Price Exception =>" + pid + " :" + ex.getMessage());
		}
		return ResponseEntity.ok(service.findById(pid));
	}
}
