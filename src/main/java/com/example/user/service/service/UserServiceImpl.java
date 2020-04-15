package com.example.user.service.service;

import com.example.user.service.dto.UserData;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

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
