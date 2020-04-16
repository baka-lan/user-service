package com.example.user.service.service;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserData> getAll() {
        return userMapper.userToUserData(userRepo.findAllByDeleted(false));
    }

    @Override
    public UserData getOne(UUID uuid) {
        User user = userRepo.findOneByUuidAndDeleted(uuid, false);
        return userMapper.userToUserData(user);
    }

    @Override
    public UserData create(UserCreateCommand userCreateCommand) {
        userCreateCommand.setPassword(passwordEncoder.encode(userCreateCommand.getPassword()));
        return userMapper.userToUserData(userRepo.save(userMapper.userCreateCommandToUser(userCreateCommand)));
    }

    @Override
    public UserData update(UUID uuid, UserUpdateCommand userUpdateCommand) {
        User user = userRepo.getOne(uuid);
        String login = userUpdateCommand.getLogin();
        if (!login.isEmpty()) {
            user.setLogin(login);
        }
        return userMapper.userToUserData(userRepo.save(user));
    }

    @Override
    public UserData changePassword(UUID uuid, UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException {
        User user = userRepo.getOne(uuid);
        if (passwordEncoder.matches(userChangePasswordCommand.getOldPass(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userChangePasswordCommand.getNewPass()));
        } else {
            throw new IllegalAccessException();
        }
        return userMapper.userToUserData(userRepo.save(user));
    }

    @Override
    public void delete(UUID uuid) {
        User user = userRepo.findOneByUuidAndDeleted(uuid, false);
        user.setDeleted(true);
        userRepo.save(user);
    }
}
