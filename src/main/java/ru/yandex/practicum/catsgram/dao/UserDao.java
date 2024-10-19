package ru.yandex.practicum.catsgram.dao;

import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;


public interface UserDao {
    User findByEmail(String email);
    List<User> findAll();
    User create(User user);
    User update(User user);
}
