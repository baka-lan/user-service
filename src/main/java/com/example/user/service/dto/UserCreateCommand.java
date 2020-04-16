package com.example.user.service.dto;

import lombok.Data;

@Data
public class UserCreateCommand {
    private String login;
    private String password;
}
