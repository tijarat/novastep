package com.step.nova.service;

import com.step.nova.dto.RegisterRequest;
import com.step.nova.entity.Role;
import com.step.nova.entity.User;
import com.step.nova.repo.RoleRepository;
import com.step.nova.repo.UserRepository;
import com.step.nova.security.JwtUtil;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,RoleRepository roleRepository,JwtUtil jwtUtil,BCryptPasswordEncoder passwordEncoder) 
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest req) 
    {
        Role role = roleRepository.findById(req.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(role);
        user.setDateOfBirth(req.getDateOfBirth());
        user.setAccountStatus("PENDING");
        return userRepository.save(user);
    }

    public String login(String email, String rawPassword) 
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) 
            throw new RuntimeException("Invalid credentials");
        if (!"ACTIVE".equals(user.getAccountStatus())) 
        {
            throw new RuntimeException("Account not active");
        }
        String role = "ROLE_" + user.getRole().getRoleName().toUpperCase();
        return jwtUtil.generateToken(user.getEmail(), role);
    }

    public User approveUser(Long userId) 
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setAccountStatus("ACTIVE");
        user.setEmailVerified("Y");
        return userRepository.save(user);
    }
    
    public User getUserById(Long id) 
    {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public List<User> getAllUsers() 
    {
        return userRepository.findAll();
    }    
}