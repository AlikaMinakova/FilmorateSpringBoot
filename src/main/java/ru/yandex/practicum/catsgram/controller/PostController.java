package ru.yandex.practicum.catsgram.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController // также благодаря этой анотации, не нужно писать @ResponseBody перед каждым
                //методом, где возвращаются данные в JSON формате
@Slf4j
public class PostController {

    private final PostService postService;

    @Autowired // внедряем зависимость (Spring ищет этот класс в бинах (@Component / @Service))
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(value = "size", defaultValue = "10") String size,
                              @RequestParam(value = "from", defaultValue = "1") String from) {
        // обработчик запроса: posts?size=4&from=2
        return postService.findAll(Integer.parseInt(size), Integer.parseInt(from));
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable("postId") int id) {
        // @PathVariable - получить значение из строки запроса, имя в параметре и переменной пути должны совпадать
        return postService.findById(id);
    }

    @GetMapping("/posts/{author}/posts")
    public void findById(@PathVariable String author,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")LocalDate from,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")LocalDate to) {
        //@RequestParam - обработчик жлементов url адреса
        //@DateTimeFormat - подстроит входную строку в LocalDate по паттерну
        //такой запрос обработает: posts/name/posts?from=22.11.2006&to=03.12.2008
        log.info("обработан запрос вида: posts/name/posts?from=22.11.2006&to=03.12.2008");
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) { // @RequestBody - получить значения из Body
        return postService.create(post);
    }

}
