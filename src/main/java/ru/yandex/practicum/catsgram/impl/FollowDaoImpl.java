package ru.yandex.practicum.catsgram.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.Follow;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FollowDaoImpl implements FollowDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public List<Post> getFollowFeed(String user_id, int max) {
        String sql = "select id_author from cat_follow where id_follower = ?";
        List<Follow> follows = jdbcTemplate.query(sql, new RowMapper<Follow>() {
            @Override
            public Follow mapRow(ResultSet rs, int rowNum) throws SQLException {
                String id_author = rs.getString("id_author");
                return new Follow(id_author,  user_id);
            }
        }, user_id);
        Set<User> users = follows
                .stream()
                .map(x -> userDao.findByEmail(x.getAuthor()))
                .collect(Collectors.toSet());
        List<Post> posts = new ArrayList<>();
        for (User user:users) {
            posts.addAll(postDao.findAllByUser(user));
        }
        return posts.stream().limit(max).toList();
    }
}
