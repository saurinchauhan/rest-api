package com.apis.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.apis.RestApiApplication;
import com.apis.dto.EmployeeDto;
import com.apis.model.Employee;
import com.apis.repository.EmployeeRepository;

public class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    EmployeeRepository employeeRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
    }

    @Test
    void testFindByFirstName() {
        Employee emp = new Employee();
        emp.setEmpNo(1);
        emp.setFirstName("adsad");
        emp.setGender("M");
        emp.setHireDate(new Date());
        emp.setLastName("asdad");
        emp.setBirthDate(new Date());

        List<Employee> employees = new ArrayList<>();
        employees.add(emp);

        Optional<List<Employee>> empList = Optional.ofNullable(employees);

        // when(employeeRepository.findByFirstName(anyString())).thenReturn(empList);
        List<EmployeeDto> result = employeeServiceImpl.findByFirstName("sdad");

        result.forEach(System.out::println);

        assertNotNull(result);
        assertEquals(result.get(0).getFirstName(), emp.getFirstName());

    }
}
