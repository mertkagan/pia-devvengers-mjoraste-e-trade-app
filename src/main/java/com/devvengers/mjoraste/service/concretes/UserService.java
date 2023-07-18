package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;



    public User findByUserId(Long userId){
            User user = userRepository.findById(userId).orElse(null);

            if (user != null) {
                return  user;
            }else {
                return null;
            }
    }


}
