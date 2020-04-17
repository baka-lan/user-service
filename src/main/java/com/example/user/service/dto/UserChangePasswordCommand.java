package com.example.user.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UserChangePasswordCommand {
    @Size(min = 3, message = "Password should be greater than 3 symbols")
    private String oldPass;

    @Size(min = 3, message = "Password should be greater than 3 symbols")
    private String newPass;
}
