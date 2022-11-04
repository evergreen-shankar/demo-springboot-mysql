package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByDepartment(String department);

}
