package com.naphtaly_prortfolio.user_service.mapping;

import com.naphtaly_prortfolio.user_service.dto.UserResponse;
import com.naphtaly_prortfolio.user_service.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse mapToUserResponse(User user){
        if(user == null){
            throw new IllegalArgumentException("Can not map null to UserResponse");
        }
        return UserResponse.builder()
                .id(user.getId())
                .cellphoneNumber(user.getCellphoneNumber())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }
}
