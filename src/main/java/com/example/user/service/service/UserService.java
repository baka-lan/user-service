package com.example.user.service.service;

import com.example.user.service.dto.UserData;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserData> getAll();

    public UserData getOne(UUID uuid);

    @Transactional
    public UserData create(UserData userData);

    @Transactional
    public UserData update(UUID uuid, UserData userData);

    @Transactional
    public void delete(UUID uuid);
}
