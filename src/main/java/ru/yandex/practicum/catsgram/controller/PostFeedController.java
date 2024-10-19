package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.FollowService;

import java.util.List;

@RestController
@Slf4j
public class PostFeedController {

    private final FollowService followService;

    @Autowired
    public PostFeedController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/feed/posts/{follower_id}")
    public List<Post> findFollowers(@PathVariable String follower_id,
                                    @RequestParam(value = "max", defaultValue = "10") String max){
         return followService.findPostsByAuthorOfFollower(follower_id, Integer.valueOf(max));
    }


}
