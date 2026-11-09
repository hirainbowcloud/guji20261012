package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
     @Autowired
     ProductService srv;
     
     @GetMapping()
     public ResponseEntity<List<Product>> getAll() {
     	    List<Product> data = srv.getAll();
     	    if (data != null && data.size() > 0) {
     	    	   return ResponseEntity.ok(data);
     	    } else {
     	    	   return ResponseEntity.noContent().build();
     	    }
     }
     
     @GetMapping("/{id}")
     public ResponseEntity<Product> findById(@PathVariable int id) {
     	     Optional<Product> op = srv.getByProductId(id);
     	     if (op.isEmpty()) {
     	    	    return ResponseEntity.notFound().build();
     	     } else {
     	    	    return ResponseEntity.ok(op.get());
     	     }
     }
     
     @PostMapping
     public ResponseEntity<Product> addProduct(@RequestBody Product p) {
     	     Product added = srv.create(p);
     	     if (added != null) {
     	    	   return ResponseEntity.ok(added);
     	     } else {
     	    	    return ResponseEntity.noContent().build();
     	     }
     }
     
     @PutMapping("/{id}")
     public ResponseEntity<Product> updateById(@PathVariable int id, @RequestBody Product p) {
     	    Product updated = srv.update(id, p);
     	    if (updated != null) {
     	    	     return ResponseEntity.ok(updated);
     	    } else {
     	    	   return ResponseEntity.notFound().build();
     	    }
     }
     
     @DeleteMapping("/{id}")
     public ResponseEntity<Product> deleteById(@PathVariable int id) {
     	    Product deleted = srv.deleteById(id);
     	    if (deleted != null) {
     	    	     return ResponseEntity.ok(deleted);
     	    } else {
     	    	   return ResponseEntity.notFound().build();
     	    }
     }
}
