package com.example.projetocrud.controller;

import com.example.projetocrud.dto.UserDTO;
import com.example.projetocrud.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestParam String email, @RequestParam String password){
        return userService.login(email, password);
    }
}
