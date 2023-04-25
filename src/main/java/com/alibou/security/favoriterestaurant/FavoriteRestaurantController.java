package com.alibou.security.favoriterestaurant;

import com.alibou.security.restaurant.Restaurant;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user/{userId}/favorite-restaurants")
@AllArgsConstructor
public class FavoriteRestaurantController {
    private FavoriteRestaurantService favoriteRestaurantService;
    private UserRepository userRepository;

    @GetMapping("")
    public Set<Restaurant> getFavorites(@PathVariable int userId){
       User user = userRepository.findById(userId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        return user.getFavoriteRestaurants();
    }

    @PostMapping("/{restaurantId}")
    public void addToFavorites(@PathVariable int customerId,@PathVariable int restaurantId){
        favoriteRestaurantService.addToFavorites(customerId,restaurantId);
    }

    @DeleteMapping("/{restaurantId}")
    public void removeFromFavorites(@PathVariable int userId, @PathVariable int restaurantId) {
        favoriteRestaurantService.removeFromFavorites(userId, restaurantId);
    }
}
