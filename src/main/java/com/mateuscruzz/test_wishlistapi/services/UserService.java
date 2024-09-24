package com.mateuscruzz.test_wishlistapi.services;

import com.mateuscruzz.test_wishlistapi.entity.User;
import com.mateuscruzz.test_wishlistapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> existingUserByPassword = userRepository.findByPassword(user.getPassword());

        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        if (existingUserByPassword.isPresent()) {
            throw new IllegalArgumentException("User with this password already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<Void> deleteUserById(UUID id) {
        userRepository.deleteById(id);
        return Optional.empty();
    }

}