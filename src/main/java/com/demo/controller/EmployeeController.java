package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
import com.demo.services.EmployeeService;


@Controller
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	  public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String department) {
	    try {
	      List<Employee> employees = new ArrayList<Employee>();

	      if (department == null)
	    	  employeeRepository.findAll().forEach(employees::add);
	      else
	    	  employeeService.findByDepartment(department).forEach(employees::add);

	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@PostMapping("/employees")
	  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
	    try {
	    	Employee _employee = employeeRepository
	          .save(new Employee(employee.getName(), employee.getDepartment(), employee.getPost()));
	      return new ResponseEntity<>(_employee, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
