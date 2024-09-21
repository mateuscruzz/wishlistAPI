package com.mateuscruzz.test_wishlistapi.services;

import com.mateuscruzz.test_wishlistapi.entity.User;
import com.mateuscruzz.test_wishlistapi.entity.Wishlist;
import com.mateuscruzz.test_wishlistapi.repositories.UserRepository;
import com.mateuscruzz.test_wishlistapi.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(savedUser);
        wishlistRepository.save(wishlist);
        return savedUser;
    }

}
