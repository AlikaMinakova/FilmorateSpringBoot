package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public HashMap<String, User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public User finByEmail(@PathVariable String email) {
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
