package com.everyoneLovesCats.service;

import com.everyoneLovesCats.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceUser {

    List<User> getAllUserService();
    User getUserByIdService(long id);
    User getUserWithNameAndPasswordService(String name, String password);
    void updateUserByIdService(long id, User user);
    void addUserService(User user);
    void deleteUserById(long id);

}
