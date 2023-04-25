package com.alibou.security.photo;


import com.alibou.security.restaurant.Restaurant;
import com.alibou.security.restaurant.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurant/{restaurantId}/photos")
public class PhotoController {

    private RestaurantRepository restaurantRepository;
    private PhotoRepository photoRepository;

    @PostMapping("")
    public ResponseEntity<Photo> createPhoto(@PathVariable int restaurantId, @RequestBody Photo photo){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            Restaurant restaurant = optionalRestaurant.get();
            photo.setRestaurant(restaurant);
            restaurant.getPhotos().add(photo);
            photoRepository.save(photo);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public List<Photo> getAllPhotosByRestaurantId(@PathVariable int restaurantId){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getPhotos();
        }else {
            return Collections.emptyList();
        }
    }

    @PatchMapping ("/{photoId}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable int restaurantId,@PathVariable int photoId,@RequestBody Photo photo){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
            if(optionalPhoto.isPresent()){
                Photo existingPhoto = optionalPhoto.get();
                existingPhoto.setImageUrl(photo.getImageUrl());
                existingPhoto.setDescription(photo.getDescription());
                photoRepository.save(existingPhoto);
                return ResponseEntity.ok(existingPhoto);
            }else {
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable int restaurantId, @PathVariable int photoId){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
            if(optionalPhoto.isPresent()){
                Photo photo = optionalPhoto.get();
                photoRepository.delete(photo);
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
