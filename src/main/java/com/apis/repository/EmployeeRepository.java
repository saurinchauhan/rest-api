package com.apis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apis.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

List<Employee>  findByFirstName(String firstName);
}
