package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 private final Map<String, Product> productStore = new ConcurrentHashMap<>();
	 
	 // 初始一些測試資料
	    @PostConstruct
	    public void initTestData() {
	        productStore.put("1", new Product("iPhone 15", "Apple 最新手機", new BigDecimal("29999"), 100));
	        productStore.put("2", new Product("MacBook Pro", "Apple 筆記型電腦", new BigDecimal("59999"), 50));
	        productStore.put("3", new Product("AirPods Pro", "無線耳機", new BigDecimal("7999"), 200));
	    }
	
	// DELETE /api/products/{id} - 刪除產品
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
	        if (!productStore.containsKey(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        Product p=productStore.remove(id);
	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok(p); //會回傳刪除的物件
	    }
	 
	 // PUT /api/products/{id} - 更新產品
	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
	        if (!productStore.containsKey(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        Product existingProduct = productStore.get(id);
	        existingProduct.setName(product.getName());
	        existingProduct.setDescription(product.getDescription());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setStock(product.getStock());
	        existingProduct.updateTimestamp();
	        
	        return ResponseEntity.ok(existingProduct);
	    }
	 
	 @PostMapping("/form")
	    public ResponseEntity<Product> createFormProduct(@ModelAttribute Product product) {
	        if (product.getName() == null || product.getName().trim().isEmpty()) {
	            return ResponseEntity.badRequest().build(); // status code: 400
	        }
	        
	        Product newProduct = new Product(
	            product.getName(),
	            product.getDescription(),
	            product.getPrice(),
	            product.getStock()
	        );
	        
	        productStore.put(newProduct.getId(), newProduct);
	        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	    }
	 
	// POST /api/products - 建立新產品	 
	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        if (product.getName() == null || product.getName().trim().isEmpty()) {
	            return ResponseEntity.badRequest().build(); // status code: 400
	        }
	        
	        Product newProduct = new Product(
	            product.getName(),
	            product.getDescription(),
	            product.getPrice(),
	            product.getStock()
	        );
	        
	        productStore.put(newProduct.getId(), newProduct);
	        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	    }
	    
	 /*
	 @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Map<String, Object> product) {
	        if (product.get("name") == null || product.get("name").toString().trim().isEmpty()) {
	            return ResponseEntity.badRequest().build(); // status code: 400
	        }
	        
	        Product newProduct = new Product(
	            product.get("name").toString(),
	            product.get("description").toString(),
	            new BigDecimal(product.get("price").toString()),
	            Integer.parseInt(product.get("stock").toString())
	        );
	        
	        productStore.put(newProduct.getId(), newProduct);
	        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	    }
	    */
	 
	// GET /api/products/{id} - 取得特定產品
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable String id) {
	        return Optional.ofNullable(productStore.get(id))
	                //.map(ResponseEntity::ok)
	        		    .map(p->ResponseEntity.ok(p))
	                .orElse(ResponseEntity.notFound().build());
	    }
	 
	 // PowerSheell指令:Invoke-RestMethod -Uri "http://localhost:8080/api/products" -Method Get
	 // GET /api/products - 取得所有產品
	    @GetMapping
	    public ResponseEntity<List<Product>> getAllProducts() {
	        List<Product> products = new ArrayList<>(productStore.values());
	        return ResponseEntity.ok(products);
	    }
	 
	
}