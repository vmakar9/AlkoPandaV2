package com.alibou.security.favoriterestaurant;

import com.alibou.security.restaurant.Restaurant;
import com.alibou.security.restaurant.RestaurantRepository;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteRestaurantService {
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public void addToFavorites(Integer customerId,Integer restaurantId){
       User user = userRepository.findById(customerId).orElseThrow(()->  new RuntimeException("Customer not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not found"));

        user.getFavoriteRestaurants().add(restaurant);
        restaurant.getFavoritedByCustomers().add(user);

        userRepository.save(user);
        restaurantRepository.save(restaurant);
    }

    public void removeFromFavorites(Integer customerId,Integer restaurantId){
        User user = userRepository.findById(customerId).orElseThrow(()->  new RuntimeException("Customer not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not found"));

        user.getFavoriteRestaurants().remove(restaurant);
        restaurant.getFavoritedByCustomers().remove(user);

        userRepository.save(user);
        restaurantRepository.save(restaurant);
    }

}
