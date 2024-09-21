package com.mateuscruzz.test_wishlistapi.repositories;

import com.mateuscruzz.test_wishlistapi.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WishlistRepository extends JpaRepository<Wishlist, UUID> {
}
