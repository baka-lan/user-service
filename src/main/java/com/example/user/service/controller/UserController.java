package com.example.user.service.controller;

import com.example.user.service.dto.UserDTO;
import com.example.user.service.mapper.UserMapper;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDTO> list() {
        return userMapper.userToDTO(userRepo.findAll());
    }

    @GetMapping("{id}")
    public UserDTO getOne(@PathVariable("id") User user) {
        return userMapper.userToDTO(user);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userMapper.userToDTO(userRepo.save(userMapper.dtoToUser(userDTO)));
    }

    @PutMapping("{id}")
    public UserDTO update(@PathVariable("id") User userFromDb, @RequestBody UserDTO userDTO) {
        UserDTO userFromDbDTO = userMapper.userToDTO(userFromDb);
        BeanUtils.copyProperties(userMapper.dtoToUser(userDTO), userFromDbDTO, "id");
        return userMapper.userToDTO(userRepo.save(userMapper.dtoToUser(userFromDbDTO)));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}
