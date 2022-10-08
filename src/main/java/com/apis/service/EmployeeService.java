package com.apis.service;

import java.util.List;

import com.apis.dto.EmployeeDto;


public interface EmployeeService {

	List<EmployeeDto> findByFirstName(String name);
	
	EmployeeDto save(EmployeeDto employeeDto);
	
	EmployeeDto update(Integer id, EmployeeDto employeeDto);
	
	EmployeeDto delete(Integer id);
	
}
