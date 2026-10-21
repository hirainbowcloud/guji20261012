package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="coffees")
@Data
public class Coffee {	
   @Id	
   @Column(name="COF_NAME",length=32)	
   String cofName;
   
   @Column(name="SUP_ID")
   int supId;
   
   BigDecimal price;
   int sales;
   int total;
   
}