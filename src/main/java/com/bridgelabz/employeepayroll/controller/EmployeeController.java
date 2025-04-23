package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.LoginRequestDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.model.EmployeeInfo;
import com.bridgelabz.employeepayroll.model.User;
import com.bridgelabz.employeepayroll.repository.UserRepository;
import com.bridgelabz.employeepayroll.service.EmployeeService;
import com.bridgelabz.employeepayroll.util.JwtUtility;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/employee-payroll-service")
@Validated
public class EmployeeController {


    // Injecting Service class
    @Autowired
    private EmployeeService employeeservice;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtility jwtUtility;

    // Update Employee using employeeID
    @PutMapping("/update/{id}")
    public ResponseDTO updateUser(@PathVariable(value="id") Long id,@Valid @RequestBody EmployeeDTO emp){
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
        log.info(String.valueOf(employee.getStartDate()));
        emp.setProfilePic(employee.getProfilePic());
        return employeeservice.createUser(emp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteEmployee(@PathVariable(value="id") Long id){
        return employeeservice.deleteEmployee(id);

    }

    @PostMapping("/register")
    public ResponseDTO registerUser(@RequestBody User user){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseDTO("User Registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return new ResponseDTO("User not found", HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        boolean isPasswordValid = bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            return new ResponseDTO("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        String jwtToken = jwtUtility.generateJwt(user.getEmail());
        user.setToken(jwtToken);
        userRepository.save(user);

        return new ResponseDTO("Login successful and token generated", HttpStatus.OK);
    }




}
