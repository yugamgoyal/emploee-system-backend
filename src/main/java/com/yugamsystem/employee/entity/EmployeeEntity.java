package com.yugamsystem.employee.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Incretmental value is will be added
    private long id;
    private String firstName;
    private String lastName;
    private String emailID;
}
