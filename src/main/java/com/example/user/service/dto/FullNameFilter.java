package com.example.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FullNameFilter {
    private String firstname;
    private String middlename;
    private String lastname;
    private boolean deleted;
}
