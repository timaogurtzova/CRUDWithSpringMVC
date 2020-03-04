package com.everyoneLovesCats.service;

import com.everyoneLovesCats.dao.UserDao;
import com.everyoneLovesCats.exception.DBException;
import com.everyoneLovesCats.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceUser {

    private static ServiceUser serviceUser;

    private final UserDao userDAO;

    @Autowired
    private ServiceUser(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public static ServiceUser getInstance() {
        return serviceUser;
    }

    public List<User> getAllUserService() {
        List<User> users = null;
        try {
            users = userDAO.getAllUser();
        } catch (DBException e) {
            //ignore
        }
        return users;
    }

    public User getUserByIdService(long id) {
        User user = null;
        try {
            user = userDAO.getUserById(id);
        } catch (DBException e) {

        }
        return user;
    }

    public User getUserWithNameAndPasswordService(String name, String password) {
        User user = null;
        try {
            if (name != null && password != null)
                user = userDAO.getUserWithNameAndPassword(name, password);
        } catch (DBException e) {

        }
        return user;
    }

    public void updateUserByIdService(long id, User user) {
        try {
            if (user != null) {
                userDAO.updateUserById(id, user);
            }
        } catch (DBException e) {

        }
    }

    public void addUserService(User user) {
        try {
            if (user != null) {
                userDAO.addUser(user);
            }
        } catch (DBException e) {

        }
    }

    public void deleteUserById(long id) {
        try {
            userDAO.deleteUserById(id);
        } catch (DBException e) {

        }
    }

}