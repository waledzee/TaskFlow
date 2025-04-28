package com.waled.TaskFlow.Service;


import com.waled.TaskFlow.Model.Role;
import com.waled.TaskFlow.Model.User;
import com.waled.TaskFlow.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public User findByUserName(String username) {
        System.out.println("Searching for username: " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
    public User CreateUser(User user)
    {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        return userRepository.save(user);
    }
}
