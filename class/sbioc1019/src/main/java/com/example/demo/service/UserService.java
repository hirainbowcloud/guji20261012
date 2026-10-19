package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {
	static List<User> data=new ArrayList<>();
	   static {
		   data.add(new User("A","Amy"));
		   data.add(new User("B","Bob"));
		   data.add(new User("C","Celiene"));
	   }
	   public Optional<User> getUserById(String id){
		   return data.stream().filter(u->u.getId().equalsIgnoreCase(id)).findAny();
	   }
	   
	   public User create(User u)
	   {
		   if(u.getId() != null && u.getName() != null)
		   {
			   data.add(u);
			   return u;
		   }
		   else
		   {
			   return null;
		   }
	   }
	   
	   public boolean delete(String id)
	   {
		   boolean flag = data.removeIf(u -> u.getId().equalsIgnoreCase(id));
		   if(flag)
		   {
			   System.out.println("User id "+id+" Deleted");
		   }
		   else
		   {
			   System.out.println("Failed to delete User id "+id);
		   }
		   return flag;
	   }
}
