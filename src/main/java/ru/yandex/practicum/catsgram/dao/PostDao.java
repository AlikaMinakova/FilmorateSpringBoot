package ru.yandex.practicum.catsgram.dao;

import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostDao {
    Collection<Post> findAllByUser(User user);
    Collection<Post> findAll(Collection<User> users, Integer size, String sort);
}
