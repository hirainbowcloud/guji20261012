package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

@Service
public class StudentService 
{
	static List<Student> data = new ArrayList();
	static 
	{
		data.add(new Student(1,"Kiki","0989"));
		data.add(new Student(2,"Lala","0985"));
		data.add(new Student(3,"DiDi","0999"));
	}
	
	public List<Student> getAll()
	{
		return data;
	}
	
	public Student getStudentById(int id)
	{
		Student student = data.stream()
				.filter(s -> s.getId() == id)
				.findFirst()
				.orElse(null);
		return student;
	}
	
	public Student createStudent(Student student)
	{
		 /*Student lastStudent = data.get(data.size() -1);
		 int lastId = lastStudent.getId();*/
		 
		 if(student!=null && student.getId()>=0 && student.getName()!=null) 
		 {
			 int nextId = data.stream()
		                .mapToInt(Student::getId)
		                .max()
		                .orElse(0) + 1;

		        // 2. 設定新產生的 ID
		        student.setId(nextId);

		        // 3. 加入清單
		        data.add(student);
			   return student;
		 }
		 else 
		 {
			   return null;
		 }		 
		
		/*
		int newId = data.stream().mapToInt(Student::getId).max().orElse(0)+1;
		student.setId(newId);
		data.add(student);
		return student;
		*/
		
	}
	
	public Student updateStudent(int id, Student updateData)
	{
		return data.stream()
				.filter(s -> s.getId() == id)
				.findFirst()
				.map(existingStudent -> {
					existingStudent.setName(updateData.getName());
					existingStudent.setPhone(updateData.getPhone());
					return existingStudent;
				})
				.orElse(null);
		
		/*
		 *  Optional<Student> found=data.stream().filter(x->x.getId()==id).findAny();
	     *  if(found.isPresent()) {
		 *    Student sx=found.get();
		 *    sx.setName(s.getName());
		 *    sx.setPhone(s.getPhone());
		 *    return sx; 
	     *  }else {
		 *    return null;
	     *  }
		 * 
		 */			
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
