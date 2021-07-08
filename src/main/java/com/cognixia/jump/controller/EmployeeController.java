package com.cognixia.jump.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.jump.model.Employee;
import com.cognixia.jump.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class EmployeeController {
	
	// creates an instance of a class (service) for you
	// create the object
	// created when application runs
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = service.getAllEmployees();
		
		System.out.println("employees = " + employees);
		
		return employees;
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {

		return service.getEmployeeById(id);
	}
	
	@DeleteMapping("/delete/employee/{id}")
	public Employee deleteEmployee(@PathVariable int id) {
		
		Employee deleted = service.deleteEmployee(id);

		System.out.println("deleted = " + deleted);
		
		return deleted;
	}
	
	@PutMapping("/update/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		Employee updated = service.updateEmployee(employee);
		
		return updated;
	}
	
	@PostMapping("/add/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		
		
		Employee newEmployee = service.createEmployee(employee);
		
		// 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(newEmployee.getId())
								.toUri();

		return ResponseEntity.created(location)
							.header("name", newEmployee.getFirstName())
							.body(newEmployee);
		
	}
	
}
