package com.example.profileservice.services;

import com.example.profileservice.model.User;

public interface UserService {
    void setDataCreatedByUser(Long id);
    User getUserById(Long id);
    User create(User user);
    User updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
