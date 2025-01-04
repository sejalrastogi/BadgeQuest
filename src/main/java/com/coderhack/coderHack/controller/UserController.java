package com.coderhack.coderHack.controller;

import com.coderhack.coderHack.entity.User;
import com.coderhack.coderHack.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all user sorted based on score
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId userId){
        return userService.getById(userId);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.saveEntry(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateScore(@RequestBody User user){
        return userService.updateUser(user.getUserName(), user.getScore());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ObjectId userId){
        userService.deleteUser(userId);
    }
}
