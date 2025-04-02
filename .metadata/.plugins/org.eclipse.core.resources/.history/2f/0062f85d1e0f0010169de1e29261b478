package com.akanksh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akanksh.dto.ApiResponse;
import com.akanksh.dto.RegisterUserDto;
import com.akanksh.exception.InvalidCredentialsException;
import com.akanksh.exception.UserAlreadyExistsException;
import com.akanksh.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            ApiResponse userDto = userService.loginUser(email, password);
            return ResponseEntity.ok(userDto);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse("An error occurred: " + e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        try {
            ApiResponse response = userService.registerUser(registerUserDto);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse("An error occurred: " + e.getMessage(), null));
        }
    }
}
