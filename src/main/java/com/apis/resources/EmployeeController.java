package com.apis.resources;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "New Employee Saved", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) })})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
		return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)),
				EmployeeDto.class);
	}
}
