package com.example.user.service.repository;

import com.example.user.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByDeleted(boolean deleted);
    User findOneByUuidAndDeleted(UUID uuid, boolean deleted);
}
