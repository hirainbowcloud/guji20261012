package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;
import java.util.*;
import com.example.demo.model.*;

@RestController
@RequestMapping("/students")
public class StudentController {
     @Autowired
     StudentService srv;
     
     @GetMapping()
     public  ResponseEntity<List<Student>> getAll(){
    	    List<Student> data=srv.getAll();
    	    if(data !=null && data.size()>0) {
    	    	   return ResponseEntity.ok(data);
    	    }else {
    	    	   return ResponseEntity.noContent().build();
    	    }
    	    
     }
     @GetMapping("/{id}")
     public ResponseEntity<Student> findById(@PathVariable int id){
    	     Optional<Student> op= srv.getByStudentId(id);
    	     if(op.isEmpty()) {
    	    	    return ResponseEntity.notFound().build();
    	     }else {
    	    	    return ResponseEntity.ok(op.get());
    	     }
     }
     
     @PostMapping
     public  ResponseEntity<Student> addStudent(@RequestBody Student s){
    	     Student added=srv.create(s);
    	     if(added!=null)
    	    	   return ResponseEntity.ok(added);
    	     else
    	    	    return ResponseEntity.noContent().build();
    	     
     }
     
     @PutMapping("/{id}")
     public ResponseEntity<Student> updateById(@PathVariable int id ,@RequestBody Student s){
    	    Student updated=srv.update(id, s);
    	    if(updated!=null) {
    	    	     return ResponseEntity.ok(updated);
    	    }else {
    	    	   return ResponseEntity.notFound().build();
    	    }
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<Student> deleteById(@PathVariable int id ){
    	    Student deleted=srv.deleteById(id);
    	    if(deleted!=null) {
    	    	     return ResponseEntity.ok(deleted);
    	    }else {
    	    	   return ResponseEntity.notFound().build();
    	    }
     }
}
