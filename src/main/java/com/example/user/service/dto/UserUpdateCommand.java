package com.example.user.service.dto;

import lombok.Data;

@Data
public class UserUpdateCommand {
    private String login;
    private String firstname;
    private String middlename;
    private String lastname;
}
