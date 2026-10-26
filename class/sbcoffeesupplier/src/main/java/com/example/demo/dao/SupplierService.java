package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Coffee;
import com.example.demo.model.Supplier;

import java.util.*;

@Service
public class SupplierService {
   @Autowired
   SupplierRepository repo;
   
   public List<Supplier> findAll(){
	   return repo.findAll();
   }
   
   public Supplier create(Supplier sup)
   {
	   try 
	   {
		   List<Coffee>cfs = sup.getCoffees();
		   cfs.forEach(c -> c.setSupplier(sup));
		   Supplier ret = repo.save(sup);
		   return ret;
	   }
	   catch(Exception ex) 
	   {
		   System.out.println("create Supplier Error: "+ex.getMessage());
		   return null;
	   }
   }
}
