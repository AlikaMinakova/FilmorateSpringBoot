package ru.yandex.practicum.catsgram.controller;

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

    private HashMap<String, User> users = new HashMap<>();

    @GetMapping("")
    public HashMap<String, User> findAll() {
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
