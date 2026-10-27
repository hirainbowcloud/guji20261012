package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

//修改商品時，所有欄位皆為選填（不含 id，id 從 URL PathVariable 取得）
@Data
public class ProductUpdateRequest {

@NotBlank(message = "商品名稱不得為空")
private String name;

@Positive(message = "價格必須大於 0")
private Double price;

@Min(value = 0, message = "庫存不可為負數")
private Integer stock;
private String category;

//public String getName() { return name; }
//public void setName(String name) { this.name = name; }
//public Double getPrice() { return price; }
//public void setPrice(Double price) { this.price = price; }
//public Integer getStock() { return stock; }
//public void setStock(Integer stock) { this.stock = stock; }
//public String getCategory() { return category; }
//public void setCategory(String category) { this.category = category; }
}
