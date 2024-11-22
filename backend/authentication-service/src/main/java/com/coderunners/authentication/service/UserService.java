package com.coderunners.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coderunners.authentication.entity.User;
import com.coderunners.authentication.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> rateUser(Integer id, int rating) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRating(rating);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> blockUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlocked(true);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}