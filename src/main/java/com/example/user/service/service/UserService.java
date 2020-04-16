package com.example.user.service.service;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.UUID;

public interface UserService {

    Page<UserData> getAll(Pageable pageable);

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
