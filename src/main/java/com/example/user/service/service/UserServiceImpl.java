package com.example.user.service.service;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Page<UserData> getAll(Pageable pageable) {
        Page<User> userPage = userRepo.findAllByDeleted(false, pageable);
        return userPage.map(userMapper::userToUserData);
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
        User user = userRepo.findOneByUuidAndDeleted(uuid, false);
        String login = userUpdateCommand.getLogin();
        if (!login.isEmpty()) {
            user.setLogin(login);
        }
        return userMapper.userToUserData(userRepo.save(user));
    }

    @Override
    public UserData changePassword(UUID uuid, UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException {
        User user = userRepo.findOneByUuidAndDeleted(uuid, false);
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
