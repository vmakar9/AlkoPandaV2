package com.alibou.security.user;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void uploadProfilePhoto(int userId, MultipartFile file) throws IOException{
        User user = userRepository.findById(userId).orElseThrow(()->  new RuntimeException("User not found"));
        user.setProfilephoto(file.getBytes());
        userRepository.save(user);
    }

    public Resource downoladProfilePhoto(int userId){
        User user = userRepository.findById(userId).orElseThrow(() ->  new RuntimeException("User not found"));
        ByteArrayResource resource = new ByteArrayResource(user.getProfilephoto());
        return  resource;
    }

    public void deleteProfile(int userId){
        User user = userRepository.findById(userId).orElseThrow(() ->  new RuntimeException("User not found"));
        user.setProfilephoto(null);
        userRepository.save(user);
    }

    public User getUser(int userId){
        return userRepository.findById(userId).get();
    }

    public User updateUser(int userId,User user){
        User userdetails = userRepository.findById(userId).get();
        userdetails.setFirstname(user.getFirstname());
        userdetails.setLastname(user.getLastname());
        return userRepository.save(userdetails);
    }
}
