package com.Qloron.usersAuth.controllers;

import com.Qloron.usersAuth.model.Users;
import com.Qloron.usersAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public Users addUser(@RequestBody Users users){
        System.out.println("Registering user ");
        return userService.createUser(users);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("Logging in");
        return userService.verify(user);
    }

}
