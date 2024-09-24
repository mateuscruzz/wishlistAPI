package com.mateuscruzz.test_wishlistapi.repositories;

import com.mateuscruzz.test_wishlistapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
