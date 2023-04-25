package com.alibou.security.restaurant;

import com.alibou.security.photo.Photo;
import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;

    private String name;
    private String directorname;
    private String description;
    private Long rating;

    private boolean parking;

    private boolean wifi;

    private Long minprice;
    private Long maxprice;

    private Long averageprice;

    @ManyToMany(mappedBy = "favoriteRestaurants")
    private Set<User> favoritedByCustomers= new HashSet<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();
}
