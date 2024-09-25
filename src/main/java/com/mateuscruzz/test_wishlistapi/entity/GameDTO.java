package com.mateuscruzz.test_wishlistapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GameDTO {
    private Long id;
    private String name;
    private String url;

    private Set<Long> similar_games  = new HashSet<>();

    @JsonProperty("storyline")
    private String storyLine;
}
