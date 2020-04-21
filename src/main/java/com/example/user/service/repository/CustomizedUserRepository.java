package com.example.user.service.repository;

import com.example.user.service.dto.FullNameFilter;
import com.example.user.service.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedUserRepository {
    List<User> findByFullName(FullNameFilter filter, Pageable pageable);
}
