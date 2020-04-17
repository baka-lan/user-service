package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateCommand {
    @NotBlank(message = "Login cannot be empty")
    private String login;
}
