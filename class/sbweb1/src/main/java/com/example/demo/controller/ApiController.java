package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;

@RestController
@RequestMapping("/api")
public class ApiController 
{
	// 實際路由 = 類別前綴 /api + 方法路徑 /hello
	
	//http://localhost:8080/api/hello
    @GetMapping("/hello")
    public GreetingResponse hello(@RequestParam(defaultValue = "World") String name) 
    {
        // 回傳物件 → Jackson 自動序列化為 JSON
    	    GreetingResponse g1= new GreetingResponse("Hello, " + name + "!", System.currentTimeMillis());
        return g1;
    }
    
    @GetMapping("/person")
    public Person createPerson()
    {
    		Person p1 = new Person("Alice",30);
    		return p1;
    }
    
    @PostMapping("/save")
    public Person save(@ModelAttribute Person p)
    {
    		String n = p.getName();
    		p.setName("userName:"+n);
    		int age = p.getAge();
    		p.setAge(age+3);
    		return p;
    }
    
    @PostMapping("/savejson")
    public Person saveJson(@RequestBody Person p)
    {
    		String n = p.getName();
		p.setName("Server Json:"+n);
		int age = p.getAge();
		p.setAge(age+3);
		return p;
    }
    
    @GetMapping("/person/{name}/{age}")
    public Person getData(@PathVariable String name,@PathVariable int age) {
    	    Person p=new Person(name,age);
    	    return p;
    }

    // record：不可變的資料载体（Java 16+），API 回傳結構化資料時很實用
    public record GreetingResponse(String message, long timestamp) {}
}
