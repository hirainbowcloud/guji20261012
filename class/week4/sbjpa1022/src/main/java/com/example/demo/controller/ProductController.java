package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductService;
import com.example.demo.model.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService srv;
    
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
    	    return ResponseEntity.ok(srv.getAll());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getBynameContains(@PathVariable String name){
    	    return ResponseEntity.ok(srv.findByNameContain(name));
    }
    @GetMapping("/category/{ct}")
    public ResponseEntity<List<Product>> getByCategoryOrderByPrice(@PathVariable String ct){
    	    return ResponseEntity.ok(srv.findCategoryByPriceDesc(ct));
    }
    
    @GetMapping("/category/price/{ct}")
    public ResponseEntity<List<Product>> getByCategoryPrice(@PathVariable String ct){
    	    return ResponseEntity.ok(srv.findAvailableByCategory(ct));
    }
    @GetMapping("/category/price/mysql/{ct}")
    public ResponseEntity<List<Product>> getMysqlByCategoryPrice(@PathVariable String ct){
    	    return ResponseEntity.ok(srv.findMysqlAvailableByCategory(ct));
    }
}
