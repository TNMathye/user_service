package com.naphtaly_prortfolio.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(unique = false, nullable = false)
    protected String username;
    @Column(unique = false, nullable = false)
    protected String password;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column(unique = true, nullable = false)
    protected String cellphoneNumber;
}
