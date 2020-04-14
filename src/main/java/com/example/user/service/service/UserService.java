package com.example.user.service.service;

import com.example.user.service.dto.UserDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    public List<UserDTO> getAll();

    public UserDTO getOne(long id);

    @Transactional
    public UserDTO create(UserDTO userDTO);

    @Transactional
    public UserDTO update(long id, UserDTO userDTO);

    @Transactional
    public void delete(long id);
}
