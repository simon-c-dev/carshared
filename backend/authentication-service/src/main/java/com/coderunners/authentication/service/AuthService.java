package com.coderunners.authentication.service;

import com.coderunners.authentication.entity.User;
import com.coderunners.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coderunners.authentication.factory.TokenFactory;


@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenFactory tokenFactory;

    @Transactional
    public String saveUser(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()
                && repository.findByUsername(user.getUsername()).isPresent()) {
            return "There is already an account associated with this username and email.";
        } else if (repository.findByUsername(user.getUsername()).isPresent()) {
            return "There is already an account associated with this username.";
        } else if (repository.findByEmail(user.getEmail()).isPresent()) {
            return "There is already an account associated with this email.";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "User added to the system successfully.";
    }

    public String GenerateToken(String username) {
        User user = new User();
        user.setUsername(username);
        return tokenFactory.generateToken(user);
    }

    public void ValidateToken(String token) throws Exception {
        tokenFactory.validateToken(token);
    }
}
