package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // MySQL AUTO_INCREMENT
	private Long id;

	@Column(nullable = false)   // NOT NULL：商品名稱必填
	private String name;

	@Column(nullable = false)   // NOT NULL：價格必填
	private Double price;

	private Integer stock;      // 允許 null：庫存可以不設定

	//private String category;    // 允許 null：類別可以不設定
	

    // 替代方案：@JsonIgnoreProperties（可雙向輸出，但互相忽略對方的集合屬性）
    // Product.java
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name = "category_id")  
    // 自訂名稱，如果沒寫它會啟用預設命名規則，自動幫您串接：屬性名稱 + _ + 主鍵名稱
    // 在這個例子中，屬性名是 category，Category 的主鍵名是 id，所以 JPA 預設也會幫您命名為 category_id
    //如果您的公司已經有一個現成的舊資料庫，裡面的欄位叫 fk_category 或 cat_no，您就可以透過自訂名稱來完美對應
    //@JoinColumn(name = "fk_category") -- 自由對應公司既有的欄位名稱
    
    @JsonIgnoreProperties("products")  // 序列化 category 時，忽略其 List<Product> products 欄位
    private Category category;
    
	// ★ JPA 必須有無參數建構子（JPA 反射建立物件時使用）
	public Product() {}

	// 帶參數建構子，方便在測試或 Service 中快速建立物件
	public Product(String name, Double price, Integer stock, Category category) {
	       this.name = name;
	       this.price = price;
	       this.stock = stock;
	       this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
	    
	    // Getter / Setter
	    /*
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public Double getPrice() { return price; }
	    public void setPrice(Double price) { this.price = price; }
	    public Integer getStock() { return stock; }
	    public void setStock(Integer stock) { this.stock = stock; }
	    public String getCategory() { return category; }
	    public void setCategory(String category) { this.category = category; }
	     */
}
