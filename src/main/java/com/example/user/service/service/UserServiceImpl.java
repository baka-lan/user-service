package com.example.user.service.service;

import com.example.user.service.dto.UserDTO;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserDTO> getAll() {
        return userMapper.userToDTO(userRepo.findAll());
    }

    @Override
    public UserDTO getOne(long id) {
        User user = userRepo.getOne(id);
        return userMapper.userToDTO(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return userMapper.userToDTO(userRepo.save(userMapper.dtoToUser(userDTO)));
    }

    @Override
    public UserDTO update(long id, UserDTO userDTO) {
        User userFromDb = userRepo.getOne(id);
        UserDTO userFromDbDTO = userMapper.userToDTO(userFromDb);
        BeanUtils.copyProperties(userMapper.dtoToUser(userDTO), userFromDbDTO, "id");
        return userMapper.userToDTO(userRepo.save(userMapper.dtoToUser(userFromDbDTO)));
    }

    @Override
    public void delete(long id) {
        userRepo.delete(userRepo.getOne(id));
    }
}
