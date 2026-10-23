package com.digitallibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 

public class UserRequestDTO {
    @NotBlank(message="name is required field")
	@Email(message="please enter valid email")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
   
    private String username;
    
    private String role;

    @Override
    public String toString() {
        return this.email + " ### " + this.password  + " ### " +this.username  + " ### "+ this.role;
    }
}
