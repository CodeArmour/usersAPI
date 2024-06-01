package com.users.demo.controllers;

import com.users.demo.domain.dto.UserDto;
import com.users.demo.domain.entities.UserEntity;
import com.users.demo.mapper.Mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.demo.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity,UserDto> userMapper;

    public UserController(final UserService userService, final Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public final List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public final ResponseEntity<UserDto> user(@PathVariable("id") final Long id) {
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public final ResponseEntity<UserDto> addUsers(@RequestBody final UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUser = userService.addUser(userEntity);
        return new ResponseEntity<UserDto>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public final ResponseEntity<UserDto> editUser(@PathVariable final Long id, @RequestBody final UserDto user) {

        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setId(id);
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUser = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity<UserDto> delete(@PathVariable final Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}