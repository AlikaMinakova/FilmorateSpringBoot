package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@Controller
@Slf4j
public class PostFeedController {

    @PostMapping("/feed/friends")
    public List<Post> findAll(@RequestBody String body) throws JsonProcessingException {
         return new ObjectMapper().readValue(body, new TypeReference<List<Post>>(){});
    }
}
