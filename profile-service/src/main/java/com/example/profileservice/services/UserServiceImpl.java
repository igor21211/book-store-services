package com.example.profileservice.services;

import com.example.profileservice.model.User;
import com.example.profileservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public void setDataCreatedByUser(Long id) {
        repository.findById(id).map(
                user -> {
                    user.setCreateAt(new Date());
                    return repository.save(user);
                }
        ).orElseThrow(()-> new RuntimeException("User dont find with this id"));
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not found this user with this id"));
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUserById(Long id, User user) {
        return repository.findById(id).map(
                user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    return repository.save(user1);
                }
        ).orElseThrow(()-> new RuntimeException("Not found this user with this id"));
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
