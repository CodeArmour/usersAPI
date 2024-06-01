package com.users.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.demo.domain.entities.UserEntity;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{
}
