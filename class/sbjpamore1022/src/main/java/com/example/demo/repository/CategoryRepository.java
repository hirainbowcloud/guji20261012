package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	// 依名稱查詢類別（Derived Query）
    Optional<Category> findByName(String name);

    // JPQL
    // JOIN FETCH：一次查詢所有類別 + 其商品，解決 N+1 查詢問題
    // 不用 JOIN FETCH 的話，每個 Category 都會再發一次 SQL 查商品 → N+1 問題
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products")
    List<Category> findAllWithProducts();
    
    
    @Query(value = "SELECT c.id, c.name, p.id AS product_id, p.name AS product_name, p.price, p.stock " +
            "FROM categories c " +
            "LEFT JOIN products p ON c.id = p.category_id", nativeQuery = true)
    List<Category> findCategoriesWithProductsRaw();
    
    
    
}
