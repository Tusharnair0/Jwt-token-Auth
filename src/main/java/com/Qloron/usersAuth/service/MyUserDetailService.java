package com.Qloron.usersAuth.service;

import com.Qloron.usersAuth.model.UserPrincipal;
import com.Qloron.usersAuth.model.Users;
import com.Qloron.usersAuth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = userRepo.findByUsername(username);
            if (user == null){
                System.out.println("User not found");
                throw new UsernameNotFoundException("User not found : User repo Failed");
            }
            return new UserPrincipal(user);
        } catch (Exception e) {
            throw new RuntimeException("we found some error" + e.getMessage());
        }
    }
}
