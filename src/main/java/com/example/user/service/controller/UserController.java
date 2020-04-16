package com.example.user.service.controller;

import com.example.user.service.dto.UserData;
import com.example.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserData> getAll() {
        return userService.getAll();
    }

    @GetMapping("{uuid}")
    public UserData getOne(@PathVariable("uuid") String uuid) {
        return userService.getOne(UUID.fromString(uuid));
    }

    @PostMapping
    public UserData create(@RequestBody UserData userData) {
        return userService.create(userData);
    }

    @PutMapping("{uuid}")
    public UserData update(@PathVariable("uuid") String uuid, @RequestBody UserData userData) {
        return userService.update(UUID.fromString(uuid), userData);
    }

    @DeleteMapping("{uuid}")
    public void delete(@PathVariable("uuid") String uuid) {
        userService.delete(UUID.fromString(uuid));
    }
}
