package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController 
{
	@Autowired
	StudentService srv;
	
	@GetMapping()
	public ResponseEntity<List<Student>> getAll()
	{
		List<Student> data = srv.getAll();
		if(data != null && data.size() > 0)
		{
			return ResponseEntity.ok(data);
		}
		else
		{
			return ResponseEntity.noContent().build();
		}		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getById(@PathVariable int id) {
	    Student student = srv.getStudentById(id);

	    if (student != null) {
	        return ResponseEntity.ok(student);                  // 200 OK + body
	    } else {
	        return ResponseEntity.notFound().build();           // 404 Not Found
	    }
	}
	
	@PostMapping
	public ResponseEntity<Student>createStudent(@RequestBody Student s)
	{
		Student add = srv.createStudent(s);
		if(add != null)
		{
			return ResponseEntity.ok(add);
		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	 @PutMapping("/{id}")
     public ResponseEntity<Student> updateById(@PathVariable int id ,@RequestBody Student s){
    	    Student updated=srv.updateStudent(id, s);
    	    if(updated!=null) {
    	    	     return ResponseEntity.ok(updated);
    	    }else {
    	    	   return ResponseEntity.notFound().build();
    	    }
     }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Student> deleteById(@PathVariable int id)
	 {
		 Student del = srv.deleteById(id);
		 if(del != null)
		 {
			 return ResponseEntity.ok(del);
		 }
		 else
		 {
			 return ResponseEntity.notFound().build();
		 }
	 }
}
