package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private HashMap<String, User> users = new HashMap<>();


    public HashMap<String, User> findAll() {
        log.info("Количество залогированных юзеров: {}", users.size());
        return users;
    }


    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("invalid email");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("user already exists");
        }
        log.info("Пользователь {} сохранён", user);
        users.put(user.getEmail(), user);
        return user;
    }


    public User update(User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("invalid email");
        }
        if (users.containsKey(user.getEmail())) {
            users.get(user.getEmail()).setNickname(user.getNickname());
            users.get(user.getEmail()).setBirthday(user.getBirthday());
            return user;
        }
        users.put(user.getEmail(), user);
        return user;
    }
}
