package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;
import com.bridgelabz.employeepayroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/employee-payroll-service")
@Validated
public class EmployeeController {


    // Injecting Service class
    @Autowired
    private EmployeeService employeeservice;

    // Update Employee using employeeID
    @PutMapping("/update/{id}")
    public ResponseDTO updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody EmployeeDTO emp){
        return employeeservice.updateUser(id,emp);
    }

    // Display all employees
    @GetMapping("/display")
    public List<EmployeeInfo> displayUser(){
        return employeeservice.displayUser();
    }

    // Get Employee by employeeID
    @GetMapping("/getByID/{id}")
    public EmployeeInfo getUser(@PathVariable(value="id") Long id){
        return employeeservice.getUserByID(id);
    }
    // Create Employee using post request
    @PostMapping("/post")
    public ResponseDTO post(@Valid @RequestBody EmployeeDTO employee){
        EmployeeInfo emp = new EmployeeInfo();
        emp.setEmployeeName(employee.getName());
        emp.setSalary(employee.getSalary());
        emp.setGender(employee.getGender());
        emp.setDepartments(employee.getDepartments());
        emp.setStartDate(employee.getStartDate());
        emp.setProfilePic(employee.getProfilePic());
        return employeeservice.createUser(emp);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteEmployee(@PathVariable(value="id") Long id){
        return employeeservice.deleteEmployee(id);

    }

}
