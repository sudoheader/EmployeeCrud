package com.cognixia.jump.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Employee;

// Service class -> handle logic or validation before doing any
//					data manipulation with out database

@Service
public class EmployeeService {

	// "mock" database for employees
	private static List<Employee> employeeDatabase = new ArrayList<>();
	private static int idCounter = 1;

	// load in database (populate the list)
	static {

		employeeDatabase.add(new Employee(idCounter++, "Peppa", "Pig", LocalDate.of(2004, 5, 30)));
		employeeDatabase.add(new Employee(idCounter++, "Goku", "Son", LocalDate.of(1979, 5, 9)));
		employeeDatabase.add(new Employee(idCounter++, "Sharpa", "Evans", LocalDate.of(1985, 7, 2)));
		employeeDatabase.add(new Employee(idCounter++, "Sonic", "The Hedgehog", LocalDate.of(1991, 6, 23)));
		employeeDatabase.add(new Employee(idCounter++, "Patrick", "Star", LocalDate.of(1984, 3, 17)));
		employeeDatabase.add(new Employee(idCounter++, "Mojo", "Jojo", LocalDate.of(1970, 7, 15)));
		employeeDatabase.add(new Employee(idCounter++, "Elle", "Woods", LocalDate.of(2001, 7, 13)));

	}
	
	public List<Employee> getAllEmployees() {
		
		return employeeDatabase;
	}

	public Employee getEmployeeById(int id) {
		
		Employee employee = employeeDatabase.stream()
										.filter( s -> s.getId() == id ) // find by id
										.findFirst() // grab first employee found
										.orElse(new Employee() ); // if not found, return new employee
		
		return employee;
	}
	
	public Employee deleteEmployee(int id) {
	
		Employee employeeToRemove = getEmployeeById(id);
		
		// remove object given from the list
		employeeDatabase.remove(employeeToRemove);
		
		return employeeToRemove;
	}
	
	public Employee updateEmployee(Employee employee) {
		
		// if employee doesn't exist, the id for toUpdate will be -1
		Employee toUpdate = getEmployeeById( employee.getId() );
		
		if( toUpdate.getId() != -1 ) {
			
			toUpdate.setFirstName(employee.getFirstName());
			toUpdate.setLastName(employee.getLastName());
			toUpdate.setDob(employee.getDob());
		}
		
		return toUpdate;
	}
	
	public Employee createEmployee(Employee employee) {
		
		// don't grab id, want to generate that ourselves
		Employee newEmployee = 
				new Employee(idCounter++, employee.getFirstName(),
						employee.getLastName(), employee.getDob());
		
		employeeDatabase.add(newEmployee);
		
		return newEmployee;
	}
	
}
