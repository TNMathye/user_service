package com.naphtaly_prortfolio.user_service.controller;

import com.naphtaly_prortfolio.user_service.dto.LoginRequest;
import com.naphtaly_prortfolio.user_service.dto.UserResponse;
import com.naphtaly_prortfolio.user_service.model.User;
import com.naphtaly_prortfolio.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        // Check if the user already exists
        if (userService.userExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with this email.");
        }
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }


    @GetMapping("/{userID}")
    public ResponseEntity<UserResponse> getById(@PathVariable("userID") Long id){
        Optional<UserResponse> userResponse = Optional.ofNullable(userService.getUserById(id));
        return userResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{emailID}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable("emailID") String email){
        Optional<UserResponse> userResponse = Optional.ofNullable(userService.getUserByEmail(email));
        return userResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestBody LoginRequest loginRequest){
        Optional<UserResponse> userResponse = Optional.ofNullable(userService.login(loginRequest));
        return userResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        List<UserResponse> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}
