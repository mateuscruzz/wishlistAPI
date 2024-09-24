package com.mateuscruzz.test_wishlistapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID gameId;

        private String title;

        private String platform;

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Game game)) return false;
            return Objects.equals(gameId, game.gameId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(gameId);
        }
}
