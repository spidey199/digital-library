package com.digitallibrary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitallibrary.dto.UserRequestDTO;
import com.digitallibrary.dto.UserResponseDto;
import com.digitallibrary.entity.User;
import com.digitallibrary.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@RestController  
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired 
    UserService userService;
    
    @PostMapping ("/login")
    public UserResponseDto login(@RequestBody @Valid UserRequestDTO userReqDto){
        System.out.println("###### Login ######");

        return userService.login(userReqDto.getEmail(), userReqDto.getPassword());

    }

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRequestDTO userRequestDTO){
        System.out.println(userRequestDTO.toString());
        
        return userService.registerUser(userRequestDTO);
        
    }

}
