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

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "middlename", nullable = false)
    private String middlename;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

}
