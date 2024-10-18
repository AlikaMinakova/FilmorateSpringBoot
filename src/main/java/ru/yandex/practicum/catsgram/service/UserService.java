package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Collection<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("invalid email");
        }
        userDao.create(user);
        return user;
    }


    public User update(User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("invalid email");
        }
        return userDao.update(user);
    }
}
