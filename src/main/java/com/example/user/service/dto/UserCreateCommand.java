package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateCommand {
    @NotBlank(message = "Login cannot be empty")
    private String login;

    @Size(min = 3, message = "Password should be greater than 3 symbols")
    private String password;
}
