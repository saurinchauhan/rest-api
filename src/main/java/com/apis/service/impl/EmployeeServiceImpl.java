package com.apis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.apis.model.Employee;
import com.apis.repository.EmployeeRepository;
import com.apis.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findByFirstName(String name) {
		return employeeRepository.findByFirstName(name);
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee update(Integer id, Employee employee) {
		Optional<Employee> employeeObj = employeeRepository.findById(id);
		if (employeeObj.isPresent()) {
			Employee emp = employeeObj.get();
			if (!employee.getFirstName().isBlank())
			emp.setFirstName(employee.getFirstName());

			if (!employee.getLastName().isBlank())
			emp.setLastName(employee.getLastName());

			if (!employee.getGender().isBlank())
			emp.setGender(employee.getGender());

			if (null != employee.getBirthDate())
			emp.setBirthDate(employee.getBirthDate());

			if (null != employee.getHireDate())
			emp.setHireDate(employee.getHireDate());

			return employeeRepository.save(emp);
		}
		throw new ResourceNotFoundException("No employee found with EmpNo : " + id);

	}

	@Override
	public Employee delete(Integer id) {
		Optional<Employee> emplOptional = employeeRepository.findById(id);
		if(emplOptional.isPresent()) {
			Employee employee = emplOptional.get();
			employeeRepository.delete(employee);
			return employee;
		}
		throw new ResourceNotFoundException("No employee found with EmpNo : " + id);
	}

}
