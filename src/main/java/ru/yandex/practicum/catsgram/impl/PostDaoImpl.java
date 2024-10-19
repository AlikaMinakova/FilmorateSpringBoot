package ru.yandex.practicum.catsgram.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class PostDaoImpl implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAllByUser(User user) {
        String sql = "select * from cat_post where author = ? order by createdate desc";
        return jdbcTemplate.query(sql, new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = Integer.valueOf(rs.getString("id"));
                LocalDate createdate = LocalDate.parse(rs.getString("createdate"));
                String description = rs.getString("description");
                String photourl = rs.getString("photourl");
                return new Post(id, user, createdate, description, photourl);
            }
        }, user.getEmail());
    }

    @Override
    public List<Post> findAll(Collection<User> users, Integer size, String sort) {
        String sql = "select * from cat_post order by createdate " + sort + " limit " + size;
        return jdbcTemplate.query(sql, (rs, rowNum) -> makePost(rs, users));
    }


    public Post makePost(ResultSet rs, Collection<User> users) throws SQLException {
        Integer id = Integer.valueOf(rs.getString("id"));
        LocalDate createdate = LocalDate.parse(rs.getString("createdate"));
        String emailAuthor = rs.getString("author");
        String description = rs.getString("description");
        String photourl = rs.getString("photourl");
        return new Post(id, users.stream().filter(x -> x.getEmail().equals(emailAuthor)).toList().get(0), createdate, description, photourl);
    }
}
