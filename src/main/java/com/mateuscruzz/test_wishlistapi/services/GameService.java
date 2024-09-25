package com.mateuscruzz.test_wishlistapi.services;

import com.mateuscruzz.test_wishlistapi.client.IGDBClient;
import com.mateuscruzz.test_wishlistapi.entity.Game;
import com.mateuscruzz.test_wishlistapi.entity.User;
import com.mateuscruzz.test_wishlistapi.repositories.GameRepository;
import com.mateuscruzz.test_wishlistapi.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IGDBClient igdbClient;

    @Setter
    @Getter
    @Value("${igdb.client-id}")
    private String clientId;

    @Setter
    @Getter
    @Value("${igdb.access-token}")
    private String accessToken;

    public Game addGameToUserWishlist(UUID userId, Game game) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        game.setUser(user);
        user.getWishlist().add(game);
        userRepository.save(user);
        return game;
    }

    public void removeGameFromUserWishlist(UUID userId, Long gameId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        user.getWishlist().remove(game);
        gameRepository.delete(game);
        userRepository.save(user);
    }

    public String searchGames(String clientId, String accessToken, String query) {
        String authorization = "Bearer " + accessToken;
        return igdbClient.searchGames(clientId, authorization, query);
    }

}
