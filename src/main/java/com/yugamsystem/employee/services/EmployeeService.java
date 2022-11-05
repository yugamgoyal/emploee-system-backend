package com.yugamsystem.employee.services;

import com.yugamsystem.employee.entity.EmployeeEntity;
import com.yugamsystem.employee.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<Employee> createEmployee(Employee employee);
    List<EmployeeEntity> getEmployees(String name);
    List<Employee> getAllEmployees();
}
