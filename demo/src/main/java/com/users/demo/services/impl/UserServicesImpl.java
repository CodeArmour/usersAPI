package com.users.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.users.demo.domain.entities.UserEntity;
import com.users.demo.repository.UserRepo;
import com.users.demo.services.UserService;

@Service
public class UserServicesImpl implements UserService{

    private final UserRepo userRepo;

    public UserServicesImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}