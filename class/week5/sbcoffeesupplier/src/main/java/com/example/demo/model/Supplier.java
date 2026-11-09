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
   
   @OneToMany(mappedBy = "supplier", targetEntity=Coffee.class , cascade = CascadeType.ALL   ) 
   @JsonIgnoreProperties("supplier") 
   List<Coffee> coffees =new ArrayList<>();
}
