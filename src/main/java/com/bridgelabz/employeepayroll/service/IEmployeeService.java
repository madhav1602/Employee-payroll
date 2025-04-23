package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;

import java.util.List;

public interface IEmployeeService {

    public ResponseDTO deleteEmployee(Long id);

    public ResponseDTO updateUser(Long id, EmployeeDTO empDTO);

    public List<EmployeeInfo> displayUser();

    public EmployeeInfo getUserByID(Long id);


    public ResponseDTO createUser(EmployeeDTO emp);
}
