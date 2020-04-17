package com.example.user.service.controller;

import com.example.user.service.dto.UserChangePasswordCommand;
import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.dto.UserUpdateCommand;
import com.example.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
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
    public UserData getOne(@PathVariable("uuid") UUID uuid) {
        return userService.getOne(uuid);
    }

    @PostMapping
    public UserData create(@RequestBody @Valid UserCreateCommand userCreateCommand) {
        return userService.create(userCreateCommand);
    }

    @PutMapping("{uuid}")
    public UserData update(@PathVariable("uuid") UUID uuid, @RequestBody @Valid UserUpdateCommand userUpdateCommand) {
        return userService.update(uuid, userUpdateCommand);
    }

    @PutMapping("{uuid}/change-password")
    public UserData changePassword(@PathVariable("uuid") UUID uuid,
                           @RequestBody @Valid UserChangePasswordCommand userChangePasswordCommand)
            throws IllegalAccessException {
        return userService.changePassword(uuid, userChangePasswordCommand);
    }

    @DeleteMapping("{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) {
        userService.delete(uuid);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IllegalAccessException exception! check arguments!")
    @ExceptionHandler(value ={IllegalAccessException.class})
    public void handleIOException() {
        log.error("IllegalAccessException handler executed");
    }
}
