package com.example.user.service.service;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserData> getAll();

    UserData getOne(UUID uuid);

    @Transactional
    UserData create(UserCreateCommand userCreateCommand);

    @Transactional
    UserData update(UUID uuid, UserUpdateCommand userUpdateCommand);

    @Transactional
    UserData changePassword(UUID uuid, UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException;

    @Transactional
    void delete(UUID uuid);
}
