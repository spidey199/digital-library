package com.digitallibrary.service;

import org.springframework.stereotype.Service;

import com.digitallibrary.dto.UserRequestDTO;
import com.digitallibrary.dto.UserResponseDto;
import com.digitallibrary.entity.User;

@Service 
public interface UserService {

    public User registerUser(UserRequestDTO user);
    public User getUserById(int userid);
    public User getUserByEmail(String email);
    public UserResponseDto login(String username, String password);

    
}
