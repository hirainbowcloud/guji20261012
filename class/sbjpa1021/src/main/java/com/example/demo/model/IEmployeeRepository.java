package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * <Employee, Long>
      ▲       ▲
   【實體類別】 【主鍵型態】 Long必須是類別 指的是Employee資料表主鍵 long id
 * */
@Repository  // 標記為 Spring 元件，讓 Spring 管理它的生命週期
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    // 繼承 JpaRepository 後，以下方法全部自動可用，不需要額外撰寫：
    //
    // ✅ save(employee)      → INSERT（id 為 null）或 UPDATE（id 有值）
    // ✅ findById(id)        → SELECT WHERE id = ?，回傳 Optional<Employee>
    // ✅ findAll()           → SELECT * 查詢全部資料
    // ✅ deleteById(id)      → DELETE WHERE id = ?
    // ✅ existsById(id)      → 確認某個 id 是否存在，回傳 boolean
    // ✅ count()             → SELECT COUNT(*)，回傳總筆數	
	//List<Employee> findByNameContaining(String keyword) → 相當於 SQL: name LIKE '%關鍵字%'
	//List<Employee> findByNameContainingOrDepartmentContaining
	//(String nameKeyword, String deptKeyword) → 相當於 SQL: name LIKE '%k1%' OR department LIKE '%k2%'
	List<Employee> findByDepartment(String dep);
}