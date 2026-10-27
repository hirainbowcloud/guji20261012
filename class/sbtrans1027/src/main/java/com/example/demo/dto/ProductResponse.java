package com.example.demo.dto;

import com.example.demo.model.Product;

import lombok.Data;

//回傳給客戶端的資料格式（不含 stock 庫存數量，避免暴露庫存資訊）
@Data
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String category;
    // 注意：stock 故意不放在這裡

    // 靜態工廠方法（Static Factory Method）：Entity → DTO，方便在 Controller 中轉換
    public static ProductResponse from(Product product) {
        ProductResponse resp = new ProductResponse();
        resp.id = product.getId();
        resp.name = product.getName();
        resp.price = product.getPrice();
        resp.category = product.getCategory(); // String（完成 Day 2 練習 2-3 後改為 .getCategory().getName()）
        return resp;
    }

    // 只有 Getter（Response 物件只讀，不需要 Setter）
//    public Long getId() { return id; }
//    public String getName() { return name; }
//    public Double getPrice() { return price; }
//    public String getCategory() { return category; }
}
