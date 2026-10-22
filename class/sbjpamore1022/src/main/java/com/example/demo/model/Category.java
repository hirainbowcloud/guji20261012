package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="categories")
@Data
public class Category {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	    
	 @Column(nullable = false, unique = true)  // 類別名稱不可重複
	 private String name;
	 
	 @OneToMany(mappedBy = "category", targetEntity=Product.class, cascade = CascadeType.ALL)
	 @JsonIgnoreProperties("category")   // 序列化 products 時，忽略每個 product 的
	 private List<Product> products = new ArrayList<>();
	 
	 public Category() {}
	 public Category(String name) { this.name = name; }

//	    public Long getId() { return id; }
//	    public void setId(Long id) { this.id = id; }
//	    public String getName() { return name; }
//	    public void setName(String name) { this.name = name; }
//	    public List<Product> getProducts() { return products; }
//	    public void setProducts(List<Product> products) { this.products = products; }

	    // ⚠️ 若有 toString()，切勿直接印出 products（會觸發 LAZY 載入並可能遞迴）
	 @Override
	 public String toString() {
	     return "Category{id=" + id + ", name='" + name + "'}";
	 }
}
