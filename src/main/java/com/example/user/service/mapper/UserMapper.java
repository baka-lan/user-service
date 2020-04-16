package com.example.user.service.mapper;

import com.example.user.service.dto.UserCreateCommand;
import com.example.user.service.dto.UserData;
import com.example.user.service.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userCreateCommandToUser(UserCreateCommand userCreateCommand);
    UserData userToUserData(User user);

    List<User> userCreateCommandToUser(List<UserCreateCommand> userCreateCommandList);
    List<UserData> userToUserData(List<User> userList);
}
