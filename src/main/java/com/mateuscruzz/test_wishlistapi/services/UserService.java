package com.mateuscruzz.test_wishlistapi.services;

import com.mateuscruzz.test_wishlistapi.entity.User;
import com.mateuscruzz.test_wishlistapi.entity.Wishlist;
import com.mateuscruzz.test_wishlistapi.repositories.UserRepository;
import com.mateuscruzz.test_wishlistapi.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    public User createUser(User user) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> existingUserByPassword = userRepository.findByPassword(user.getPassword());

        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        if (existingUserByPassword.isPresent()) {
            throw new IllegalArgumentException("User with this password already exists");
        }

        User savedUser = userRepository.save(user);
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(savedUser);
        wishlistRepository.save(wishlist);
        return savedUser;
    }

}
