package com.example.user.service.service;

import com.example.user.service.dto.UserData;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Override
    public List<UserData> getAll() {
        return userMapper.userToUserData(userRepo.findAll());
    }

    @Override
    public UserData getOne(UUID uuid) {
        User user = userRepo.getOne(uuid);
        return userMapper.userToUserData(user);
    }

    @Override
    public UserData create(UserData userData) {
        return userMapper.userToUserData(userRepo.save(userMapper.userDataToUser(userData)));
    }

    @Override
    public UserData update(UUID uuid, UserData userData) {
        User userFromDb = userRepo.getOne(uuid);
        BeanUtils.copyProperties(userMapper.userDataToUser(userData), userFromDb, "uuid");
        return userMapper.userToUserData(userRepo.save(userFromDb));
    }

    @Override
    public void delete(UUID uuid) {
        userRepo.delete(userRepo.getOne(uuid));
    }
}
