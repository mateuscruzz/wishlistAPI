package com.mateuscruzz.test_wishlistapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "igdbClient", url = "https://api.igdb.com/v4")
public interface IGDBClient {

    @PostMapping("/games")
    String searchGames(@RequestHeader("Client-ID") String clientId,
                       @RequestHeader("Authorization") String authorization,
                       @RequestBody String query);
}
