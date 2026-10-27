package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

//新增商品時，客戶端傳入的資料（不含 id，id 由資料庫自動產生）
@Data
public class ProductCreateRequest {

// (1) @NotBlank：不允許 null、空字串 ""、純空白字串 "   "
@NotBlank(message = "商品名稱不得為空")
private String name;     // 必填（驗證規則在練習 3-3 加入）

// (2) @Positive：值必須嚴格大於 0（0 也不行）
@Positive(message = "價格必須大於 0")
private Double price;    // 必填

// (3) @Min(value = 0)：最小值為 0，允許 0（庫存可以是 0，但不可以是負數）
@Min(value = 0, message = "庫存不可為負數")
private Integer stock;   // 選填
private String category; // 選填

//public String getName() { return name; }
//public void setName(String name) { this.name = name; }
//public Double getPrice() { return price; }
//public void setPrice(Double price) { this.price = price; }
//public Integer getStock() { return stock; }
//public void setStock(Integer stock) { this.stock = stock; }
//public String getCategory() { return category; }
//public void setCategory(String category) { this.category = category; }
}
