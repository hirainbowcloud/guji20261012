package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.model.*;

@Service
public class StudentService {
   static List<Student> data =new ArrayList(List.of(new Student(1,"Alan","123456"),
		   new Student(2,"Mary","13579"), new Student(3,"George","24680")));
   
   public List<Student> getAll(){
	   return data;
   }
   
   public Optional<Student> getByStudentId(int id) {
	   return data.stream().filter(s->s.getId()==id).findAny();
   }
   
   public Student create(Student s) {
	   if(s!=null && s.getId()>0 && s.getName()!=null) {
		   data.add(s);
		   return s;
	   }else {
		   return null;
	   }
   }
   public Student update(int id,Student s) {
	   Optional<Student> found=data.stream().filter(x->x.getId()==id).findAny();
	   if(found.isPresent()) {
		   Student sx=found.get();
		   sx.setName(s.getName());
		   sx.setPhone(s.getPhone());
		   return sx; 
	   }else {
		   return null;
	   }
   }
   public Student deleteById(int id) {
	   Optional<Student> found=data.stream().filter(x->x.getId()==id).findAny();
	   if(found.isPresent()) {
		   Student sx=found.get();
		   data.remove(sx);
		   return sx;
	   }else {
		   return null;
	   }
   }
}
