package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service // работа бизнес логики (чтоб не смешивать с обработчиками url запросов) // тоже что и @Controller
@Slf4j
public class PostService {

    private List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        log.info("Текущее количество постов: {}", posts.size());
        return posts;
    }

    public Post create(Post post) {
        log.info("Пост {} сохранён", post);
        posts.add(post);
        return post;
    }
}
