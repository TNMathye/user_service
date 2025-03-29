package com.naphtaly_prortfolio.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    protected Long id;
    protected String username;
    protected String password;
    protected String email;
    protected String cellphoneNumber;
}
