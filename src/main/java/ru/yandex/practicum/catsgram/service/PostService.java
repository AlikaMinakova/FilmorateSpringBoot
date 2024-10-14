package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // работа бизнес логики (чтоб не смешивать с обработчиками url запросов) // тоже что и @Controller
@Slf4j
public class PostService {

    private List<Post> posts = new ArrayList<>();

    public List<Post> findAll(int size, int from) {

        log.info("Получены посты: {}", posts.size());
        return posts.stream()
                .sorted(Comparator.comparing(Post::getId)) // отсортирует по возрастагнию
                .filter(x -> x.getId() >= from)
                .limit(size).toList();
    }

    public Optional<Post> findById(int postId) {
        log.info("Получен пост с id: {}", postId);
        return posts.stream().filter(x -> x.getId() == postId).findFirst();
    }

    public Post create(Post post) {
        log.info("Пост {} сохранён", post);
        posts.add(post);
        return post;
    }
}
