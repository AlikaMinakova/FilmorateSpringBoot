package ru.yandex.practicum.catsgram.dao;

import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.Optional;


public interface UserDao {
    Optional<User> findByEmail(String email);
    Collection<User> findAll();
    User create(User user);
    User update(User user);
}
