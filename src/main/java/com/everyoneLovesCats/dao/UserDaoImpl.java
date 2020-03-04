package com.everyoneLovesCats.dao;

import com.everyoneLovesCats.exception.DBException;
import com.everyoneLovesCats.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUser() throws DBException {
        List<User> users;

        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("SELECT user FROM User user");
            users = query.list();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DBException {
        User user;

        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public User getUserWithNameAndPassword(String name, String password) throws DBException {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "SELECT u FROM User u WHERE u.name =:nameUser AND u.password =:passwordUser ")
                    .setParameter("nameUser", name)
                    .setParameter("passwordUser", password)
                    .setMaxResults(1);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DBException(e);
        }

        return user;
    }

    @Override
    public void updateUserById(long id, User newParameterUser) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            user.setName(newParameterUser.getName());
            user.setAge(newParameterUser.getAge());
            user.setPassword(newParameterUser.getPassword());
            user.setCity(newParameterUser.getCity());
            user.setRole(newParameterUser.getRole());
            session.save(user);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(User user) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById(long id) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("DELETE FROM User WHERE id =:param");
            query.setParameter("param", id);
            query.executeUpdate();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}
