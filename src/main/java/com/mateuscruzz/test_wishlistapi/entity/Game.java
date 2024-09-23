package com.mateuscruzz.test_wishlistapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
