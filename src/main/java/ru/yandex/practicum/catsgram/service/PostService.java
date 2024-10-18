package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service // работа бизнес логики (чтоб не смешивать с обработчиками url запросов) // тоже что и @Controller
@Slf4j
public class PostService {

    private final PostDao postDao;
    private final UserService userService;

    @Autowired
    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findAllByUser(String userId) {
        User user = userService.findByEmail(userId).orElseThrow(() -> new UserNotFoundException("пользователь не найден"));
        return postDao.findAllByUser(user);
    }

    public Collection<Post> findAllByUser(String userId, Integer size, String sort){
        return findAllByUser(userId)
                .stream()
                .sorted((p0, p1) -> {
                    int comp = p0.getCreateDate().compareTo(p1.getCreateDate());
                    if (sort.equals("desc")) {
                        comp = -1 * comp;
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

    public Collection<Post> findAll(int size, String sort) {
        Collection<User> user = userService.findAll();
        return postDao.findAll(user, size, sort);
    }

//    public Post create(Post post) {
//        log.info("Пост {} сохранён", post);
//        posts.add(post);
//        return post;
//    }
}
