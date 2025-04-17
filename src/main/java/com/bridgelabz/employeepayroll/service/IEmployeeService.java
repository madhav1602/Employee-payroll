package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;

import java.util.List;

public interface IEmployeeService {

    public void deleteEmployee(Long id);
    public String updateUser(Long id, EmployeeDTO empDTO);
    public List<EmployeeInfo> displayUser();
    public EmployeeInfo getUserByID(Long id);
    public String createUser(EmployeeInfo emp);
}
