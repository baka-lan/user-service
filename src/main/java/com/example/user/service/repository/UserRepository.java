package com.example.user.service.repository;

import com.example.user.service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, CustomizedUserRepository {
    Page<User> findAllByDeleted(boolean deleted, Pageable pageable);
    User findOneByUuidAndDeleted(UUID uuid, boolean deleted);
}
