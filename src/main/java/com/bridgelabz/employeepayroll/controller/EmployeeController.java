package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;
import com.bridgelabz.employeepayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee-payroll-service")
public class EmployeeController {


    // Injecting Service class
    @Autowired
    private EmployeeService employeeservice;

    // Update Employee using employeeID
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable(value="id") Long id, @RequestBody EmployeeDTO emp){
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
    public String post(@RequestBody EmployeeDTO employee){
        EmployeeInfo emp = new EmployeeInfo();
        emp.setEmployeeName(employee.getName());
        emp.setSalary(employee.getSalary());
        employeeservice.createUser(emp);
        return "User created successfully";

    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable(value="id") Long id){
        employeeservice.deleteEmployee(id);
    }

}
