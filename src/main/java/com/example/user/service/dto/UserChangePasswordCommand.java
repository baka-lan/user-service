package com.example.user.service.dto;

import lombok.Data;

@Data
public class UserChangePasswordCommand {
    private String oldPass;
    private String newPass;
}
