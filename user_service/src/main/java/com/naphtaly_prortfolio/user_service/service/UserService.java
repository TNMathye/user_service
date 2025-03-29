package com.naphtaly_prortfolio.user_service.service;

import com.naphtaly_prortfolio.user_service.dto.LoginRequest;
import com.naphtaly_prortfolio.user_service.dto.UserResponse;
import com.naphtaly_prortfolio.user_service.model.User;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public UserResponse getUserById(Long uID);
    public List<UserResponse> getAllUsers();
    public UserResponse login(LoginRequest loginRequest);
    public UserResponse getUserByEmail(String email);
    public User createUser(User user);
    public boolean userExists(String email);
}
