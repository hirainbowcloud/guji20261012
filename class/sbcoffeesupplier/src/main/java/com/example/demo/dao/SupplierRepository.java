package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
      // JpaRepository.findAll() -> 取得所有供應商資料
}
