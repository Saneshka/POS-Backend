package com.saneshka.pos.service;

import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.User;

@Service
public interface UserService {
    User createUser(User user);
}
