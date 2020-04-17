package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UserChangePasswordCommand {
    @Min(value = 3, message = "Password should be greater than 3 symbols")
    private String oldPass;

    @Min(value = 3, message = "Password should be greater than 3 symbols")
    private String newPass;
}
