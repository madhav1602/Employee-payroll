package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.LoginRequestDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.model.User;
import com.bridgelabz.employeepayroll.repository.UserRepository;
import com.bridgelabz.employeepayroll.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtility jwtUtility;

    public ResponseDTO register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseDTO("User Registered successfully", HttpStatus.CREATED);
    }
    public ResponseDTO login(LoginRequestDTO loginRequest){
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
