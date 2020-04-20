package com.example.user.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserData {
    private UUID uuid;
    private String login;
    private String firstname;
    private String middlename;
    private String lastname;
}
