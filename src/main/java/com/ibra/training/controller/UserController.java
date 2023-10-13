package com.ibra.training.controller;

import com.ibra.training.model.User;
import com.ibra.training.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(HttpServletResponse response, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return user.get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(HttpServletResponse response, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        user.setRole_name("GUEST");
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User update(HttpServletResponse response, @PathVariable("id") Long userId, @RequestBody User user) {
        Optional<User> oldUser = userRepository.findById(userId);
        if(oldUser.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return userRepository.save(user);
    }

}
