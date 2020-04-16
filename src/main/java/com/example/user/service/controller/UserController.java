package com.example.user.service.controller;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;
import com.example.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserData> getAll(@PageableDefault Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("{uuid}")
    public UserData getOne(@PathVariable("uuid") String uuid) {
        return userService.getOne(UUID.fromString(uuid));
    }

    @PostMapping
    public UserData create(@RequestBody UserCreateCommand userCreateCommand) {
        return userService.create(userCreateCommand);
    }

    @PutMapping("{uuid}")
    public UserData update(@PathVariable("uuid") String uuid, @RequestBody UserUpdateCommand userUpdateCommand) {
        return userService.update(UUID.fromString(uuid), userUpdateCommand);
    }

    @PutMapping("{uuid}/change-password")
    public UserData changePassword(@PathVariable("uuid") String uuid,
                           @RequestBody UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException {
        return userService.changePassword(UUID.fromString(uuid), userChangePasswordCommand);
    }

    @DeleteMapping("{uuid}")
    public void delete(@PathVariable("uuid") String uuid) {
        userService.delete(UUID.fromString(uuid));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IllegalAccessException exception! check arguments!")
    @ExceptionHandler(value ={IllegalAccessException.class})
    public void handleIOException() {
        System.out.println("IllegalAccessException handler executed");
    }
}
