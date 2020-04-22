package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateCommand {
    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "Middlename cannot be empty")
    private String middlename;

    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;
}
