package com.bridgelabz.employeepayroll.model;

import jakarta.persistence.*;
import lombok.Data;


import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employeeID;


    private String employeeName;
    private Double salary;
    private String gender;
    @Column(unique = true)
    private String email;
    private LocalDate startDate;
    public String profilePic;

    @ElementCollection
    @CollectionTable(name="employee_department", joinColumns = @JoinColumn)
    @Column(name="department")
    public List<String> departments;



}


