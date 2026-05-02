package com.step.nova.controller;

import com.step.nova.entity.User;
import com.step.nova.dto.LoginRequest;
import com.step.nova.dto.RegisterRequest;
import com.step.nova.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
    private final UserService userService;
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) 
    {
        User savedUser = userService.register(req);
        return ResponseEntity.ok(savedUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) 
    {
        String token = userService.login(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(token);
    }    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/verify/{userId}")
    public User verifyUser(@PathVariable Long userId) 
    {
        return userService.approveUser(userId);
    }   
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) 
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() 
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
}