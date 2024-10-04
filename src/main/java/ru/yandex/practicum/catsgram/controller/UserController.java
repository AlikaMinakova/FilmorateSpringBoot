package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private HashMap<String, User> users = new HashMap<>();

    @GetMapping("")
    public HashMap<String, User> findAll() {
        log.info("Количество залогированных юзеров: {}", users.size());
        return users;
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
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

    @PutMapping("")
    public User update(@RequestBody User user) {
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
