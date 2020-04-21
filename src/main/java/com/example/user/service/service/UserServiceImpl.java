package com.example.user.service.service;

import com.example.user.service.dto.*;
import com.example.user.service.exceptions.InvalidOldPasswordException;
import com.example.user.service.exceptions.UserNotFoundException;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserData> getAll(Pageable pageable) {
        Page<User> userPage = userRepo.findAllByDeleted(false, pageable);
        return userPage.map(userMapper::userToUserData);
    }

    @Override
    public UserData getOne(UUID uuid) {
        User user = getUser(uuid);
        return userMapper.userToUserData(user);
    }

    @Override
    public List<UserData> getByFullName(FullNameFilter fullNameFilter, Pageable pageable) {
        return userMapper.userToUserData(userRepo.findByFullName(fullNameFilter, pageable));
    }

    @Override
    @Transactional
    public UserData create(UserCreateCommand userCreateCommand) {
        userCreateCommand.setPassword(passwordEncoder.encode(userCreateCommand.getPassword()));
        return userMapper.userToUserData(userRepo.save(userMapper.userCreateCommandToUser(userCreateCommand)));
    }

    @Override
    @Transactional
    public UserData update(UUID uuid, UserUpdateCommand userUpdateCommand) {
        User user = getUser(uuid);

        String login = userUpdateCommand.getLogin();
        if (Strings.isNotBlank(login)) {
            user.setLogin(login);
        }
        String firstname = userUpdateCommand.getFirstname();
        if (Strings.isNotBlank(firstname)) {
            user.setFirstname(firstname);
        }
        String middlename = userUpdateCommand.getMiddlename();
        if (Strings.isNotBlank(middlename)) {
            user.setMiddlename(middlename);
        }
        String lastname = userUpdateCommand.getLastname();
        if (Strings.isNotBlank(lastname)) {
            user.setLastname(lastname);
        }
        return userMapper.userToUserData(userRepo.save(user));
    }

    @Override
    @Transactional
    public UserData changePassword(UUID uuid, UserChangePasswordCommand userChangePasswordCommand) {
        User user = getUser(uuid);
        if (passwordEncoder.matches(userChangePasswordCommand.getOldPass(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userChangePasswordCommand.getNewPass()));
        } else {
            throw new InvalidOldPasswordException("Old password is not correct");
        }
        return userMapper.userToUserData(userRepo.save(user));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        User user = getUser(uuid);
        user.setDeleted(true);
        userRepo.save(user);
    }

    private User getUser(UUID uuid) {
        User user = userRepo.findOneByUuidAndDeleted(uuid, false);
        if (null != user) {
            return user;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
