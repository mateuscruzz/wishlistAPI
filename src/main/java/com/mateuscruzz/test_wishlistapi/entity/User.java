package com.mateuscruzz.test_wishlistapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String username;

    private String password;

    private String email;

    @OneToOne(mappedBy = "user")
    private Wishlist wishlist;
}
