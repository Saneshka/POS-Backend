package com.saneshka.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.User;

@Service
public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
