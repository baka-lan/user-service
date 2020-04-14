package com.example.user.service.mapper;

import com.example.user.service.dto.UserDTO;
import com.example.user.service.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToUser(UserDTO userDTO);
    UserDTO userToDTO(User user);

    List<User> dtoToUser(List<UserDTO> userDTOList);
    List<UserDTO> userToDTO(List<User> userList);
}
