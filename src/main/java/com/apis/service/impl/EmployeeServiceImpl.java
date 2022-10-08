package com.apis.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.apis.dto.EmployeeDto;
import com.apis.model.Employee;
import com.apis.repository.EmployeeRepository;
import com.apis.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<EmployeeDto> findByFirstName(String name) {
		Optional<Employee> employees = employeeRepository.findByFirstName(name);
		return employees.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
				.toList();
	}

	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)),
				EmployeeDto.class);
	}

	@Override
	public EmployeeDto update(Integer id, EmployeeDto employeeDto) {
		Optional<Employee> employeeObj = employeeRepository.findById(id);
		if (employeeObj.isPresent()) {
			Employee employee = employeeObj.get();
			if (!employeeDto.getFirstName().isBlank())
				employee.setFirstName(employeeDto.getFirstName());

			if (!employeeDto.getLastName().isBlank())
				employee.setLastName(employeeDto.getLastName());

			if (!employeeDto.getGender().isBlank())
				employee.setGender(employeeDto.getGender());

			if (null != employeeDto.getBirthDate())
				employee.setBirthDate(employeeDto.getBirthDate());

			if (null != employeeDto.getHireDate())
				employee.setHireDate(employeeDto.getHireDate());

			return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
		}
		throw new ResourceNotFoundException("No employee found with EmpNo : " + id);

	}

	@Override
	public EmployeeDto delete(Integer id) {
		Optional<Employee> emplOptional = employeeRepository.findById(id);
		if(emplOptional.isPresent()) {
			Employee employee = emplOptional.get();
			employeeRepository.delete(employee);
			return modelMapper.map(employee,EmployeeDto.class);
		}
		throw new ResourceNotFoundException("No employee found with EmpNo : " + id);
	}

}
