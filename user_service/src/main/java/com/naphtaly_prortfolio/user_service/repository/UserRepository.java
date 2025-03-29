package com.naphtaly_prortfolio.user_service.repository;

import com.naphtaly_prortfolio.user_service.dto.UserResponse;
import com.naphtaly_prortfolio.user_service.model.User;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select user From User user Where user.email = :email")
    public User findByEmail(@Param("email") String email);
}
