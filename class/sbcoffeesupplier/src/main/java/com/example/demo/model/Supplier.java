package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="suppliers")
@Data
public class Supplier {
  
   @Id
   @Column(name="SUP_ID")  
   Integer supId;
   
   @Column(name="SUP_NAME")
   String supName;
   
   String street;
   String city;
   @Column(length=2)
   String state;
   @Column(length=5)
   String zip;
   
   //mappedBy = "supplier" 是public class Coffee 的Supplier supplier;
   @OneToMany(mappedBy = "supplier", targetEntity=Coffee.class , cascade = CascadeType.ALL   ) 
   //Coffee 類別內，被序列化成 JSON 時的屬性名稱，主要是為了解決「循環參照（無窮迴圈）」的問題
   //List<Coffee> coffees =new ArrayList<>();在 coffees 陣列裡的每一個 Coffee 物件，都要忽略掉它們各自的 supplier 屬性
   @JsonIgnoreProperties("supplier") 
   List<Coffee> coffees =new ArrayList<>();
}
