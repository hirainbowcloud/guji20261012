package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.*;
import com.example.demo.model.*;

import java.util.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	 @Autowired
	 SupplierService srv;
	
     @GetMapping
     public ResponseEntity<List<Supplier>> getAll(){
    	    return ResponseEntity.ok(srv.getAll());
     }
     
     @PostMapping
     public ResponseEntity<Supplier> save(@RequestBody Supplier sp){    	     
    	     Supplier rt=srv.save(sp);
    	     if(rt!=null) {
    	    	    return ResponseEntity.ok(rt);
    	     }else {
    	    	    return ResponseEntity.noContent().build();
    	     }
     }
     
}
