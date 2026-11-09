package com.example.demo.model;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class PersonDAO {
     List<Person> persons=new ArrayList<Person>(
    		 List.of(new Person(1,"Mary","Mary@example.com"),new Person(2,"George","George@example.com")));
     public List<Person> getAll(){
    	     //persons.clear();
    	     return persons;
     }
     public Optional<Person> findById(int id){
	     
	     return persons.stream().filter(p->p.getId()==id).findAny();
     }
     public boolean add(Person p){
    	      if(p!=null) {
    	    	     return  persons.add(p);
    	      }else {
    	    	     return false;
    	      }
     }
     
     public boolean remove(int  id){
	    return  persons.removeIf(p->p.getId()==id);
     }
     public Optional<Person> update(int id,Person p) {
    	     Optional<Person> person=persons.stream().filter(x->x.getId()==id).findAny();
    	     return person.map(ps->{
    	    	    ps.setEmail(p.getEmail());
    	    	    ps.setName(p.getName());
    	    	    return ps;
    	     });
     }
}
