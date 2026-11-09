package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepository;
import java.util.*;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository dao;
	
	// 查詢所有員工
    public List<Employee> findAll() {
        return dao.findAll();
    }
    // 依 id 查詢單筆（回傳 Optional，讓呼叫者自行處理找不到的情況）
    public Optional<Employee> findById(Long id) {
        return dao.findById(id);
    }
    
 // 新增員工
    public Employee create(Employee employee) {
    	   try {
    	    Employee added=dao.save(employee);
        return added;
    	   }catch(Exception ex) {
    		   System.out.println("新增employee失敗=>"+ex.getMessage());
    		   return null;
    	   }
    }
    // 修改員工資料（先確認是否存在，再更新）
    public Optional<Employee> update(Long id, Employee updatedEmployee) {
        return dao.findById(id).map(existing -> {
            existing.setName(updatedEmployee.getName());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setDepartment(updatedEmployee.getDepartment());
            existing.setSalary(updatedEmployee.getSalary());
            return dao.save(existing); // save 有 id → UPDATE
        });
    }
    
 // 刪除員工（回傳 boolean 告知呼叫者是否成功）
    public boolean delete(Long id) {
        if (dao.existsById(id)) {
        	   try {
            dao.deleteById(id);
            return true;
        	   }catch(Exception ex) {
        		   System.out.println("delete employee failed=>"+ex.getMessage());
        		   return false;
        	   }
        }
        return false;
    }
    
    public long employeeCount() {
    	    return dao.count();
    }
    
    public List<Employee> findByDepartment(String d){
    	   List<Employee> data=dao.findByDepartment(d);
    	   if(data.size()>0) {
    		   return data;
    	   }else {
    		   return new ArrayList<Employee>();
    	   }
    }
}
