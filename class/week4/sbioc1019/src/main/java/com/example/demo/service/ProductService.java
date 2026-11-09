package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public class ProductService {
   static List<Product> data = new ArrayList<>(List.of(
		   new Product(1, "Laptop", 30000),
		   new Product(2, "Phone", 15000),
		   new Product(3, "Tablet", 20000)));
   
   public List<Product> getAll() {
	   return data;
   }
   
   public Optional<Product> getByProductId(int id) {
	   return data.stream().filter(p -> p.getId() == id).findAny();
   }
   
   public Product create(Product p) {
	   if (p != null && p.getId() > 0 && p.getName() != null) {
		   data.add(p);
		   return p;
	   } else {
		   return null;
	   }
   }
   
   public Product update(int id, Product p) {
	   Optional<Product> found = data.stream().filter(x -> x.getId() == id).findAny();
	   if (found.isPresent()) {
		   Product px = found.get();
		   px.setName(p.getName());
		   px.setPrice(p.getPrice());
		   return px;
	   } else {
		   return null;
	   }
   }
   
   public Product deleteById(int id) {
	   Optional<Product> found = data.stream().filter(x -> x.getId() == id).findAny();
	   if (found.isPresent()) {
		   Product px = found.get();
		   data.remove(px);
		   return px;
	   } else {
		   return null;
	   }
   }
}
