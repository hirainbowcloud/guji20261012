package com.example.demo.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;
import java.util.*;

@Service
public class ProductService {
	private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Transactional
    public Product create(Product p) {
    	    return productRepository.save(p);
    }
    
    public List<Product> getAll(){
    	  return productRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Product findByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        // 找不到 → 拋出自訂例外 → GlobalExceptionHandler 捕獲 → 回傳 404 JSON
    }
    
    public Product findById(Long pid) {
    	      //return productRepository.findById(pid).orElseThrow();
    	      return findByIdOrThrow(pid);
    }
    
 // 問題 A：庫存扣減 + 訂單建立（如果建立訂單失敗，庫存不應被扣減）
    public void placeOrder(Long productId, int quantity) {
        deductStock(productId, quantity);  // ← 直接呼叫同類別的方法
    }

    @Transactional
    public void deductStock(Long productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow();
        p.setStock(p.getStock() - quantity);
        productRepository.save(p);        
        throw new RuntimeException("訂單建立失敗");  // 應該 rollback 但不會！
    }
    
    public int getStockById(Long productId) {
    	     Product p = productRepository.findById(productId).orElseThrow();
    	     return p.getStock();
    }
    
    @Transactional
    public void updatePrice(Long productId, Double newPrice) {
        try {
            Product p = productRepository.findById(productId).orElseThrow();
            p.setPrice(newPrice);
            productRepository.save(p);
            throw new RuntimeException("模擬失敗");
        } catch (Exception e) {
            System.out.println("發生錯誤：" + e.getMessage());
            // 吃掉例外，沒有重新拋出！
        }
    }
    @Transactional
    public Optional<Product> update(Long id, Product updated) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setStock(updated.getStock());
            existing.setCategory(updated.getCategory());
            return productRepository.save(existing);  // (6) id 有值 → UPDATE
        });
    }
    
   
}
