package com.Qloron.usersAuth.service;

import com.Qloron.usersAuth.model.Users;
import com.Qloron.usersAuth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users createUser(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println("Authentication successful");
            return jwtService.generateToken(user.getUsername());
        }
        System.out.println("Authentication Failed");
        return "fail";
    }
}
