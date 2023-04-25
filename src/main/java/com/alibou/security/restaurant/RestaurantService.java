package com.alibou.security.restaurant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void saveRestaurant(Restaurant restaurant){
        if(restaurant.getRestaurantId()>0){
            restaurantRepository.save(restaurant);
        }else {
            throw new RuntimeException("id lower that zero");
        }
    }

    public ResponseEntity<List<Restaurant>> getrestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return new ResponseEntity<>(restaurantList, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<Restaurant> getoneRestaurants(int restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        return new ResponseEntity<>(restaurant,HttpStatusCode.valueOf(200));
    }

    public void updateRestaurantById(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByName(String name){
        return new ResponseEntity<>(restaurantRepository.findRestaurantByName(name),HttpStatusCode.valueOf(200));
    }



    public ResponseEntity<List<Restaurant>> getRestuarantByRating(Long raiting){
        return new ResponseEntity<>(restaurantRepository.findByRating(raiting),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingASC(){
        return new ResponseEntity<>(restaurantRepository.sortRestuarantByRatingASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByRaitingDESC(){
        return new ResponseEntity<>(restaurantRepository.sortRestuarantByRatingDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceASC(){
        return new ResponseEntity<>(restaurantRepository.sortRestaurantByMinPriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMinPriceDESC(){
        return new ResponseEntity<>(restaurantRepository.sortRestaurantByMinPriceDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceASC(){
        return new ResponseEntity<>(restaurantRepository.sortResturantByMaxPriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByMaxPriceDESC(){
        return  new ResponseEntity<>(restaurantRepository.sortRestuarantByMaxPriceDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceASC(){
        return new ResponseEntity<>(restaurantRepository.sortRestaurantByAveragePriceASC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> sortRestaurantByAveragePriceDESC(){
        return new ResponseEntity<>(restaurantRepository.sortRestaurantByAveragePriceDESC(),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByParking(boolean parking){
        return new ResponseEntity<>(restaurantRepository.findByParking(parking),HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Restaurant>> getRestaurantByWifi(boolean wifi){
        return new ResponseEntity<>(restaurantRepository.findByWifi(wifi),HttpStatusCode.valueOf(200));
    }


}
