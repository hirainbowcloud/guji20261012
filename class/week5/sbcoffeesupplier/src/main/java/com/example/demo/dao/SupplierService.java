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
   
   public List<Supplier> getAll(){
	   return repo.findAll();
   }
   
   public Supplier save(Supplier s) {
	    List<Coffee> cfs=s.getCoffees();
	    cfs.forEach(c->c.setSupplier(s));
	    try {
	        Supplier ret=repo.save(s);
	        return ret;
	    }catch(Exception ex) {
	    	  System.out.println("Insert Supplier error =>"+ex.getMessage());
	    }
	    return null;
   }
}
