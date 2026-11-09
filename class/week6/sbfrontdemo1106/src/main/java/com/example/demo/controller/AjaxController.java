package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDAO;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AjaxController {
	@Autowired
	PersonDAO dao;
	
	
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Person>> getAll(){
		List<Person> data= dao.getAll();
		if(data!=null && data.size()>0 ) {
			return ResponseEntity.ok(data);
		}else
			return ResponseEntity.noContent().build();
	}
    
	@GetMapping("/find/{id}")
	public ResponseEntity<Person> findById(@PathVariable int id){
		Optional<Person> data= dao.findById(id);
		if(data.isPresent() ) {
			return ResponseEntity.ok(data.get());
		}else
			return ResponseEntity.notFound().build();
	}
	@PostMapping("/add")
	public ResponseEntity<String> addPerson(@RequestBody Person p){
		boolean flag=dao.add(p);
		if(flag) {
			return ResponseEntity.ok("add success");
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removePerson(@PathVariable int id){
		boolean flag=dao.remove(id);
		if(flag) {
			return ResponseEntity.ok("delete success");
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable int id,@RequestBody Person p){
		Optional<Person> edit=dao.update(id,p);
		if(edit.isPresent()) {
			return ResponseEntity.ok(edit.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/send")
    public ResponseEntity<Person> postData(@ModelAttribute Person p){
    	   if(p!=null) {
    		   return ResponseEntity.ok(p);
    	   }else {
    		   return ResponseEntity.badRequest().build();
    	   }
    }
    @GetMapping("/statuscode/{code}")
    public ResponseEntity<String> statusCode(@PathVariable int code) {
    	        switch(code) {
    	            case 200:
    	            	   return ResponseEntity.ok("OK");
    	            case 404:
    	            	   return ResponseEntity.notFound().build();
    	            case 400:
    	            	   return ResponseEntity.badRequest().build();
    	            	default:
    	            		return ResponseEntity.ok("default");
    	        }
    }
}
