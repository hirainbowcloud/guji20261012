package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Coffee;

@Repository
public interface ICoffeeRepository extends JpaRepository<Coffee,String> {

}
