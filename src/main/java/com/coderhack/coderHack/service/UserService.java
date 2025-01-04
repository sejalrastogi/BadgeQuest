package com.coderhack.coderHack.service;

import com.coderhack.coderHack.entity.Badge;
import com.coderhack.coderHack.entity.User;
import com.coderhack.coderHack.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> saveEntry(User user){
        // check if score is 0 initially
        if(user.getScore() != 0){
            return new ResponseEntity<>("Score must be 0 at the time of registration", HttpStatus.BAD_REQUEST);
        }

        // also ensure that no badges are assigned initially
        user.setBadges(new HashSet<>());

        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // get all users sorted based on score
    public List<User> getAll(){
        List<User> list = userRepository.findAll();
        Collections.sort(list, Comparator.comparingInt(User::getScore));
        return list;
    }

    public ResponseEntity<?> getById(ObjectId id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateUser(String userName, int newScore){
        User userInDB = userRepository.findByUserName(userName);

        if(userInDB == null){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        // check if the newScore is valid or not
        if(newScore < 0 || newScore > 100){ // not in valid range
            return new ResponseEntity<>("Score must be in the range from 0 to 100.", HttpStatus.BAD_REQUEST);
        }

        userInDB.setScore(newScore);

        // update badges
        Set<Badge> badges= userInDB.getBadges();
        if(newScore >= 0 && newScore < 30){
            badges.add(Badge.CODE_NINJA);
        } else if (newScore >= 30 && newScore < 60) {
            badges.add(Badge.CODE_CHAMP);
        }else{
            badges.add(Badge.CODE_MASTER);
        }

        if(badges.size() > 3){
            return new ResponseEntity<>("A user can't have more than 3 unique badges", HttpStatus.BAD_REQUEST);
        }

        userInDB.setBadges(badges);

        userRepository.save(userInDB);
        return new ResponseEntity<>(userInDB, HttpStatus.OK);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }


}
