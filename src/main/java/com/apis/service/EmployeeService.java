package com.apis.service;

import java.util.List;

import com.apis.dto.EmployeeDto;
import com.apis.model.Employee;


public interface EmployeeService {

	List<Employee> findByFirstName(String name);
	
	Employee save(Employee employee);
	
	Employee update(Integer id, Employee employee);
	
	Employee delete(Integer id);
	
}
