package com.project.Task.Managment.controller;

import com.project.Task.Managment.DTO.RegistrationDto;
import com.project.Task.Managment.DTO.userWithoutPassWordDto;
import com.project.Task.Managment.entity.AuthRequest;
import com.project.Task.Managment.entity.UserInfo;
import com.project.Task.Managment.service.JwtService;
import com.project.Task.Managment.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<userWithoutPassWordDto> addNewUser(@RequestBody @Valid RegistrationDto registrationDto) {
        UserInfo userInfo = new UserInfo(
                registrationDto.getUserName(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                "USER"
        );
        userWithoutPassWordDto userWithoutPassWordDto =
                new userWithoutPassWordDto(userInfo.getName(),userInfo.getEmail());
        service.addUser(userInfo);
        return new ResponseEntity<>(userWithoutPassWordDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
