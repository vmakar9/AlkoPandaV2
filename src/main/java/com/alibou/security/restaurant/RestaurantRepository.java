package com.alibou.security.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findRestaurantByName(String name);

    @Query("FROM Restaurant order by averageprice ASC")
    List<Restaurant> sortRestaurantByAveragePriceASC();
    @Query("FROM Restaurant order by averageprice DESC")
    List<Restaurant> sortRestaurantByAveragePriceDESC();

    @Query("FROM Restaurant order by minprice ASC ")
    List<Restaurant> sortRestaurantByMinPriceASC();

    @Query("FROM Restaurant order by minprice DESC")
    List<Restaurant> sortRestaurantByMinPriceDESC();

    @Query("FROM Restaurant order by maxprice ASC")
    List<Restaurant> sortResturantByMaxPriceASC();
    @Query("FROM Restaurant order by maxprice DESC ")
    List<Restaurant> sortRestuarantByMaxPriceDESC();
    @Query("FROM Restaurant order by rating ASC ")
    List<Restaurant> sortRestuarantByRatingASC();
    @Query("FROM Restaurant order by rating DESC ")
    List<Restaurant> sortRestuarantByRatingDESC();

    List<Restaurant> findByRating(Long rating);

    List<Restaurant> findByParking(boolean parking);

    List<Restaurant> findByWifi(boolean wifi);


}
