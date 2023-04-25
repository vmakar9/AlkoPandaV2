package com.alibou.security.user;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @PostMapping("/{userId}/photo")
    public ResponseEntity<Object> uploadProfilePhoto(@PathVariable int userId, @RequestParam MultipartFile file){
        try {
            userService.uploadProfilePhoto(userId,file);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}/photo")
    public ResponseEntity<Resource> downoladProfilePhoto(@PathVariable int userId){
        Resource resource = userService.downoladProfilePhoto(userId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

    @DeleteMapping("/{userId}/photo")
    public ResponseEntity<Object> deleteProfilePhoto(@PathVariable int userId){
        userService.deleteProfile(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/photo")
    public ResponseEntity<?> updateProfilePhoto(@PathVariable int userId,@RequestParam MultipartFile file){
        User user = userRepository.findById(userId).orElseThrow(() ->  new RuntimeException("User not found"));
        try {
            user.setProfilephoto(file.getBytes());
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable int userId,@RequestBody User user){
        return userService.updateUser(userId,user);
    }


}
