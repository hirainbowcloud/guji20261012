package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeService;
import com.example.demo.model.Employee;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService srv;
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
    	    List<Employee> data=srv.findAll();
    	    if(data!=null && data.size()>0) {
    	    	   return ResponseEntity.ok(data);
    	    }else {
    	    	  return ResponseEntity.notFound().build();
    	    }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
    	    Optional<Employee> data=srv.findById(id);
    	    if(data.isPresent()) {
    	    	   return ResponseEntity.ok(data.get());
    	    }else {
    	    	  return ResponseEntity.notFound().build();
    	    }
    }
    
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
    	     Employee added= srv.create(emp);
    	     if(added!=null) {
    	    	     return ResponseEntity.ok(added);
    	     }else {
    	    	    return ResponseEntity.noContent().build();
    	     }
    }
    
    @PostMapping("/form")
    public ResponseEntity<Employee> addFormEmployee(@ModelAttribute Employee emp){
    	     Employee added= srv.create(emp);
    	     if(added!=null) {
    	    	     return ResponseEntity.ok(added);
    	     }else {
    	    	    return ResponseEntity.noContent().build();
    	     }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable Long id,@RequestBody Employee updateEmp){
    	     Optional<Employee> updated=srv.update(id, updateEmp);
    	     if(updated.isPresent()) {
    	    	    return ResponseEntity.ok(updated.get());
    	     }else {
    	    	    return ResponseEntity.notFound().build();
    	     }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
    	     Optional<Employee> emp=srv.findById(id);
    	     boolean del=srv.delete(id);
    	     if(del) {
    	    	    return ResponseEntity.ok(emp.get());
    	     }else {
    	    	    return ResponseEntity.notFound().build();
    	     }
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count(){
    	   long c=srv.employeeCount();
    	   if(c>0) {
    		   return ResponseEntity.ok(c);
    	   }else {
    		   return ResponseEntity.noContent().build();
    	   }
    }
    @GetMapping("/department/{dept}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable String dept){
    	    List<Employee> data= srv.findByDepartment(dept);
    	    if(data.size()>0) {
    	    	   return ResponseEntity.ok(data);
    	    }else {
    	    	   return ResponseEntity.notFound().build();
    	    }
    }
}
