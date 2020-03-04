package com.everyoneLovesCats.dao;

import com.everyoneLovesCats.exception.DBException;
import com.everyoneLovesCats.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser() throws DBException;

    User getUserById(long id) throws DBException;

    User getUserWithNameAndPassword(String name, String password) throws DBException;

    void updateUserById(long id, User newParameterUser) throws DBException;

    void addUser(User user) throws DBException;

    void deleteUserById(long id) throws DBException;
}