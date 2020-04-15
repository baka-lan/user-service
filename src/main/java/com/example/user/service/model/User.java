package com.example.user.service.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

}
