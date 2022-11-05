package com.yugamsystem.employee.services;

import com.yugamsystem.employee.entity.EmployeeEntity;
import com.yugamsystem.employee.model.Employee;
import com.yugamsystem.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
       this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        try {
            EmployeeEntity e = employeeRepository.findByEmailID(employeeEntity.getEmailID());
            employeeRepository.save(employeeEntity);
            return ResponseEntity.status(HttpStatus.OK).body(employee); // 200
        } catch (RuntimeException ex) {
            log.warn("User already there");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employee); // 500

    }

    @Override
    public List<EmployeeEntity> getEmployees(String name) {
        return employeeRepository.findAllByFirstName(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().map(emp -> new Employee(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmailID())).collect(Collectors.toList());
    }
}
