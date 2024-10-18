package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public Optional<User> finByEmail(@PathVariable String email) {
        // @PathVariable - получить значение из строки запроса, имя в параметре и переменной пути должны совпадать
        return userService.findByEmail(email);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        // @RequestBody - получить значения из Body
        return userService.create(user);
    }

    @PutMapping("")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
