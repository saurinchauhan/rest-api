package com.apis.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.server.ResponseStatusException;

import com.apis.dto.EmployeeDto;
import com.apis.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
// @Component
@RequestMapping("/employee")
public class EmployeeController {
	
	final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Get list of employees by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the employees", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<EmployeeDto> getEmployeeByName(@PathVariable("name") final String name) {
		logger.info("Calling EmployeeController.getEmployeeByName :: {}",name);
		List<EmployeeDto> list = employeeService.findByFirstName(name);

		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity not found");
		}
		return  list;
	}

	@Operation(summary = "Save new Employee details")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "New Employee Saved", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
		return employeeService.save(employeeDto);
	}

	@Operation(summary = "Update Employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee details Updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDto update(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
		return employeeService.update(id, employeeDto);

	}

	@Operation(summary = "Delete employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@DeleteMapping("{id}")
	public EmployeeDto delete(@PathVariable("id") Integer id) {
		return employeeService.delete(id);
	}


	@GetMapping("health/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String healthCheck(@PathVariable("name") String name) {
		if(name.equals("error")) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal error");
		}
		return "hi";
	}
}
