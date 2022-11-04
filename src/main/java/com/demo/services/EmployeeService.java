package com.demo.services;

import java.util.List;

import com.demo.model.Employee;

public interface EmployeeService {
	List<Employee> findByDepartment(String department);

}
