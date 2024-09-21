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
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID wishlistId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    /*@OneToMany(mappedBy = "wishlist")
    private List<Game> games;*/
}
