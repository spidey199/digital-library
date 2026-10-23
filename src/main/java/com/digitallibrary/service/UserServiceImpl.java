package com.digitallibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitallibrary.dto.UserRequestDTO;
import com.digitallibrary.dto.UserResponseDto;
import com.digitallibrary.entity.User;
import com.digitallibrary.exception.UserException;
import com.digitallibrary.repository.UserRepository;

@Service 
public class UserServiceImpl implements UserService{

    @Autowired 
    UserRepository userRepo;
    // UserServiceImpl(UserRepository userRepo){
    //     this.userRepo = userRepo;
    // }

    @Override
    public User registerUser(UserRequestDTO userRequestDTO) {
        // TODO Auto-generated method stub

        User user =    User.builder().username(userRequestDTO.getUsername())
                        .email(userRequestDTO.getEmail())
                        .password(userRequestDTO.getPassword())
                        .role(userRequestDTO.getRole())
                        .build();

        System.out.println("####### " +user.toString());
        if(user!=null && !userRepo.findByEmail(user.getEmail()).isPresent()){
            return userRepo.save(user);
        }
        if(user == null)
        throw new UserException("User is null");
        else
            throw new UserException("UserAlreadyExists..!!!!");
    }

    // public User login(String username, String password){
    //     return userRepo.findByUsername(username)
    //     .filter( u -> u.getPassword().equals(password) )
    //     .orElseThrow(
    //         () -> new UserException("No username found..")
    //     );
    // }
    public UserResponseDto login(String email, String password){
        User user = userRepo.findByEmail(email)
        .filter( u -> u.getPassword().equals(password) )
        .orElseThrow(
            () -> new UserException("No email found..")
        );
        UserResponseDto respDto = new UserResponseDto();
        respDto.setUsername(user.getUsername());
        respDto.setEmail(user.getEmail());
        return respDto;
    }

        @Override
        public User getUserById(int userId) {
            return userRepo.findById(userId)
                    .orElseThrow(() ->
                            new UserException(
                                "User not found with given id: " + userId
                            )
                    );
        }

        @Override
        public User getUserByEmail(String email) {
            return userRepo.findByEmail(email)
                    .orElseThrow(() ->
                            new UserException(
                                "User not found with given email: " + email
                            )
                    );
        }
    
}
