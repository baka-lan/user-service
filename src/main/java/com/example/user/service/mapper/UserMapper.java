package com.example.user.service.mapper;

import com.example.user.service.dto.UserData;
import com.example.user.service.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDataToUser(UserData userData);
    UserData userToUserData(User user);

    List<User> userDataToUser(List<UserData> userDataList);
    List<UserData> userToUserData(List<User> userList);
}
