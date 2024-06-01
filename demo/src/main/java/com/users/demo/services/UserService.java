package com.users.demo.services;

import java.util.List;
import java.util.Optional;

import com.users.demo.domain.entities.UserEntity;

public interface UserService {

    List<UserEntity> findAll();
    Optional<UserEntity> findOne(Long id);
    UserEntity addUser(UserEntity userEntity);
    boolean isExists(Long id);
    UserEntity save(UserEntity userEntity);
    void delete(Long id);
}