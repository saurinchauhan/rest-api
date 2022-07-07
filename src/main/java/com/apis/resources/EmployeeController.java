package com.apis.resources;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apis.dto.EmployeeDto;
import com.apis.model.Employee;
import com.apis.repository.EmployeeRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get list of employees by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the employees", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<EmployeeDto> getEmployeeByName(@PathVariable("name") final String name) {
		return employeeRepository.findByFirstName(name).stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
				.toList();
	}

	@Operation(summary = "Save new Employee details")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "New Employee Saved", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
		return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)),
				EmployeeDto.class);
	}

	@Operation(summary = "Update Employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee details updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDto update(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
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

	@Operation(summary = "Delete employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@DeleteMapping("{id}")
	public EmployeeDto delete(@PathVariable("id") Integer id) {
		Optional<Employee> emplOptional = employeeRepository.findById(id);
		if(emplOptional.isPresent()) {
			Employee employee = emplOptional.get();
			employeeRepository.delete(employee);
			return modelMapper.map(employee,EmployeeDto.class);
		}
		throw new ResourceNotFoundException("No employee found with EmpNo : " + id);
	}
}
