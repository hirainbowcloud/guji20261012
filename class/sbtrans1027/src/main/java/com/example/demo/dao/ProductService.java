package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

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

	public List<Product> getAll() {
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
		return findByIdOrThrow(pid); //對應自訂方法
	}

	// 問題 A：庫存扣減 + 訂單建立（如果建立訂單失敗，庫存不應被扣減）
	public void placeOrder(Long productId, int quantity) {
		deductStock(productId, quantity); // ← 直接呼叫同類別的方法
											// 呼叫後會真的扣除庫存，不會Transactional
	}

	@Transactional
	public void deductStock(Long productId, int quantity) {
		Product p = productRepository.findById(productId).orElseThrow();
		// .orElseThrow()如果找不到這筆資料，就直接拋出異常（報錯）！
		// productRepository.findById(id) 的回傳值不是直接的 Product 物件，
		// 而是一個叫做 Optional<Product> 的「包裝盒」。
		// • 這個盒子裡可能裝著 Product（有找到），也可能是空的（沒找到，代表 ID 輸入錯誤）。
		// • 你不能直接拿空盒子來用，必須先「拆開」它。.orElseThrow() 就是最直接的拆法。
		p.setStock(p.getStock() - quantity);
		productRepository.save(p);
		throw new RuntimeException("訂單建立失敗");
		// 直接呼叫此程式，會Transcation
		// throw new RuntimeException("訂單建立失敗");
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
			// throw new RuntimeException("模擬失敗"):
			// 放在try catch會正常執行，不會rollback
			// 要放在try catch外面才會rollback
		}
	}

	@Transactional
	public Optional<Product> update(Long id, Product update) {
		return productRepository.findById(id).map(existing -> {
			existing.setName(update.getName());
			existing.setPrice(update.getPrice());
			existing.setStock(update.getStock());
			existing.setCategory(update.getCategory());
			return productRepository.save(existing); // (6) id 有值 → UPDATE
		});
	}

}
