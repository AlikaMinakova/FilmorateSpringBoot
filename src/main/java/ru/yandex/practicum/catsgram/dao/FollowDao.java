package ru.yandex.practicum.catsgram.dao;

import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

public interface FollowDao {
    public List<Post> getFollowFeed(String user_id, int max);
}
