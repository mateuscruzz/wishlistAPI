package com.mateuscruzz.test_wishlistapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateuscruzz.test_wishlistapi.client.IGDBClient;
import com.mateuscruzz.test_wishlistapi.entity.Game;
import com.mateuscruzz.test_wishlistapi.entity.GameDTO;
import com.mateuscruzz.test_wishlistapi.entity.User;
import com.mateuscruzz.test_wishlistapi.repositories.GameRepository;
import com.mateuscruzz.test_wishlistapi.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Game addGameToUserWishlist(UUID userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String query = "fields name,url,similar_games,storyline; where id = " + gameId + ";";
        String gameDTOJson = igdbClient.searchGames(clientId, "Bearer " + accessToken, query);

        List<GameDTO> gameDTOList = parseGameDTO(gameDTOJson);
        if (gameDTOList.isEmpty()) {
            throw new RuntimeException("Game not found in IGDB");
        }
        GameDTO gameDTO = gameDTOList.getFirst();

        Game game = new Game();
        game.setId(gameId);
        game.setName(gameDTO.getName());
        game.setUrl(gameDTO.getUrl());
        game.getSimilar_games().addAll(gameDTO.getSimilar_games());
        game.setStoryLine(gameDTO.getStoryLine());

        game.setUser(user);

        user.getWishlist().add(game);
        userRepository.save(user);
        return game;
    }

    private List<GameDTO> parseGameDTO(String gameDTOJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(gameDTOJson, new TypeReference<List<GameDTO>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing game info JSON", e);
        }
    }

    public void removeGameFromUserWishlist(UUID userId, Long gameId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (!user.getWishlist().contains(game)) {
            throw new RuntimeException("Game not found in user's wishlist");
        }

        user.getWishlist().remove(game);
        gameRepository.delete(game);
        userRepository.save(user);
    }

    public String searchGames(String clientId, String accessToken, String query) {
        String authorization = "Bearer " + accessToken;
        return igdbClient.searchGames(clientId, authorization, query);
    }

}
