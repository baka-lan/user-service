package com.example.user.service.controller;

import com.example.user.service.dto.UserDTO;
import com.example.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public UserDTO getOne(@PathVariable("id") long id) {
        return userService.getOne(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("{id}")
    public UserDTO update(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }
}
