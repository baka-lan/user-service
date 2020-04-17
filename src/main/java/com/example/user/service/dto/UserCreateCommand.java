package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserCreateCommand {
    @NotBlank(message = "Login cannot be empty")
    private String login;

    @Min(value = 3, message = "Password should be greater than 3 symbols")
    private String password;
}
