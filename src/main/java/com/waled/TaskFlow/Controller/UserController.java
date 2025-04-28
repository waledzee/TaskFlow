package com.waled.TaskFlow.Controller;

import com.waled.TaskFlow.Model.User;
import com.waled.TaskFlow.Repository.UserRepository;
import com.waled.TaskFlow.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<User> RegisterUser(@RequestBody User user)
    {
        User createdUser =userService.CreateUser(user);
        return new ResponseEntity<User> (createdUser, HttpStatus.CREATED);

    }

}
