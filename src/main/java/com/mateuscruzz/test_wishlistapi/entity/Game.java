package com.mateuscruzz.test_wishlistapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

        @Id
        private Long id;

        private String name;

        private String url;

        @Column(name = "story_line", length = 15000)
        @JsonProperty("storyline")
        @Size(max = 5000, message = "Story line must be less than 5000 characters")
        private String storyLine;

        @ElementCollection
        private Set<Long> similar_games  = new HashSet<>();

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Game game)) return false;
            return Objects.equals(id, game.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
