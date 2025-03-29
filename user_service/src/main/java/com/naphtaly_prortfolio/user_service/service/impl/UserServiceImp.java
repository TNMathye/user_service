package com.naphtaly_prortfolio.user_service.service.impl;

import com.naphtaly_prortfolio.user_service.dto.LoginRequest;
import com.naphtaly_prortfolio.user_service.dto.UserResponse;
import com.naphtaly_prortfolio.user_service.mapping.UserMapper;
import com.naphtaly_prortfolio.user_service.model.User;
import com.naphtaly_prortfolio.user_service.repository.UserRepository;
import com.naphtaly_prortfolio.user_service.service.UserService;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse getUserById(Long uID) {
        Optional<User> user = userRepository.findById(uID);
        return user.map(userMapper::mapToUserResponse).orElseThrow(
                () -> new PersistenceException("User Not Found")
        );
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::mapToUserResponse).toList();
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user != null && user.getPassword().equals(loginRequest.getPassword())){
            return userMapper.mapToUserResponse(user);
        }
        return null;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userMapper.mapToUserResponse(userRepository.findByEmail(email));
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public boolean userExists(String email) {
        boolean isPresent = false;
        if(userRepository.findByEmail(email) != null){
            isPresent = true;
        }
        return isPresent;
    }

}
