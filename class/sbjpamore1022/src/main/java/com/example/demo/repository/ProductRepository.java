package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>  {
	
	//JPQL Java Persistence Query Language 
	 @Query("SELECT p FROM Product p WHERE p.category.name = :cat AND p.stock > 0 ORDER BY p.price ASC")
	 List<Product> findAvailableByCategory(@Param("cat") String category);
	
	 /*SELECT id, name, price, stock
	 FROM products
	 WHERE category_id = 3 AND stock > 0
	 ORDER BY price;
	 */
	 @Query(value="select id, category,name,price,stock from demo.products where category=:cat  and stock>0  order by price", nativeQuery=true)
	 List<Product> findMysqlAvailableByCategory(@Param("cat") String category);
	 
	// (1) WHERE category = ?
    List<Product> findByCategory(String category);

    // (2) WHERE name LIKE '%keyword%'（Containing 自動加 %，不用手動加）
    List<Product> findByNameContaining(String keyword);

    // (3) WHERE price < ?
    List<Product> findByPriceLessThan(Double maxPrice);

    // (4) WHERE category = ? AND price > ?（參數順序要與方法名對應）
    List<Product> findByCategoryAndPriceGreaterThan(String category, Double minPrice);

    // (5) WHERE category = ? ORDER BY price DESC
    List<Product> findByCategoryOrderByPriceDesc(String category);

    // (6) SELECT COUNT(*) WHERE category = ?（回傳 long）
    long countByCategory(String category);

    // (7) SELECT COUNT(*) > 0 WHERE name = ?（回傳 boolean）
    boolean existsByName(String name);

}
