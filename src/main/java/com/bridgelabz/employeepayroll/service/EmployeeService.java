package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;
import com.bridgelabz.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeerepository;


    public void deleteEmployee(Long id){
        employeerepository.deleteById(id);
    }

    public String updateUser(Long id, EmployeeDTO empDTO){
        Optional<EmployeeInfo> employee=employeerepository.findById(id);

        if(employee.isEmpty()){
            return null;
        }
        EmployeeInfo emp=employee.get();

        if(empDTO.getName()!=null){
            emp.setEmployeeName(empDTO.getName());
        }
        if(!empDTO.getSalary().isNaN()){
            emp.setSalary(empDTO.getSalary());
        }
        employeerepository.save(emp);
        return "User Updated Successfully";
    }

    public List<EmployeeInfo> displayUser(){
        return (employeerepository.findAll());
    }


    public EmployeeInfo getUserByID(Long id){
        Optional<EmployeeInfo> employee=employeerepository.findById(id);
        EmployeeInfo emp= employee.get();
        return emp;
    }


    public String createUser(EmployeeInfo emp){
        employeerepository.save(emp);
        return "Added to database successfully";
    }
}
