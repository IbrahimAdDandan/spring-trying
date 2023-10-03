package com.ibra.training.controller;

import com.ibra.training.model.User;
import com.ibra.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public User save(@RequestBody String name) {
        return userRepository.save(new User(name));
    }

}
