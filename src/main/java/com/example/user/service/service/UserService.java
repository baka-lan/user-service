package com.example.user.service.service;

import com.example.user.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Page<UserData> getAll(Pageable pageable);

    UserData getOne(UUID uuid);

    List<UserData> getByFullName(FullNameFilter fullNameFilter, Pageable pageable);

    UserData create(UserCreateCommand userCreateCommand);

    UserData update(UUID uuid, UserUpdateCommand userUpdateCommand);

    UserData changePassword(UUID uuid, UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException;

    void delete(UUID uuid);
}
