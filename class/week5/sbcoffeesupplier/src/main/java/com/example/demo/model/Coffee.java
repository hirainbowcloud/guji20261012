package com.example.demo.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="coffees")
@Data
public class Coffee {	
   @Id	
   @Column(name="COF_NAME",length=80)	
   String cofName;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "SUP_ID")
   @JsonIgnoreProperties("coffees")
   @ToString.Exclude
   Supplier supplier;
   
   BigDecimal price;
   int sales;
   int total;
   
}
