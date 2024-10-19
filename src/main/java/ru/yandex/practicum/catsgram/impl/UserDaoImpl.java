package ru.yandex.practicum.catsgram.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//класс, связывающий объекты класса в приложении с таблицей в бд (User -> cat_user)
@Component
@Slf4j
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByEmail(String email) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet("select * from cat_user where email = ?", email);
        if (userRow.next()) {
            User user = new User(userRow.getString("email"),
                    userRow.getString("nickname"),
                    LocalDate.parse(Objects.requireNonNull(userRow.getString("birthday"))));
            log.info("Получен пользователь: {}", email);
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from cat_user";
        log.info("Получены все пользователи");
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs));
    }

    @Override
    public User create(User user) {
        User userOptional = findByEmail(user.getEmail());
        if (userOptional == null) {
            log.info("Ошибка создания пользователя. Уже создан");
            throw new UserAlreadyExistException("пользователь уже создан");
        }
        String sql = "insert into cat_user (email, nickname, birthday) values (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getNickname(), user.getBirthday());
        log.info("Создан пользователь {}", user.getEmail());
        return user;
    }

    @Override
    public User update(User user) {
        User userOptional = findByEmail(user.getEmail());
        if (userOptional == null) {
            log.info("Ошибка обновления пользователя. Нет в бд");
            throw new UserNotFoundException("пользователя нет в бд");
        }
        String sql = "update cat_user set nickname = ?, birthday = ? where email = ?";
        jdbcTemplate.update(sql, user.getNickname(), user.getBirthday(), user.getEmail());
        log.info("Обновлён пользователь {}", user.getEmail());
        return user;
    }

    public User makeUser(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
        String nickname = rs.getString("nickname");
        return new User(email, nickname, birthday);
    }
}
