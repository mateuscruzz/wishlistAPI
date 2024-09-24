package com.mateuscruzz.test_wishlistapi.controllers;

import com.mateuscruzz.test_wishlistapi.entity.Game;
import com.mateuscruzz.test_wishlistapi.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/{userId}")
    public ResponseEntity<Game> addGameToWishlist(@PathVariable UUID userId, @RequestBody Game game) {
        Game addedGame = gameService.addGameToUserWishlist(userId, game);
        return ResponseEntity.ok(addedGame);
    }

    @DeleteMapping("/{userId}/{gameId}")
    public ResponseEntity<Void> removeGameFromWishlist(@PathVariable UUID userId, @PathVariable Long gameId) {
        gameService.removeGameFromUserWishlist(userId, gameId);
        return ResponseEntity.noContent().build();
    }

}
