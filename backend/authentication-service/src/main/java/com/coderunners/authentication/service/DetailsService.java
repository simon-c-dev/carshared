package com.coderunners.authentication.service;

import com.coderunners.authentication.entity.User;
import com.coderunners.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.coderunners.authentication.config.DetailsConfig;

import java.util.Optional;

@Component

public class DetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credentials= repository.findByUsername(username);
        return credentials.map(DetailsConfig::new).orElseThrow(()->new UsernameNotFoundException("user not found with name:"+ username));
    }
}
