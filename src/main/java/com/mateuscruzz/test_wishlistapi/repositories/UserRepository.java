package com.mateuscruzz.test_wishlistapi.repositories;

import com.mateuscruzz.test_wishlistapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPassword(String password);
    User findByUsername(String username);
}
