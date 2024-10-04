package ru.yandex.practicum.catsgram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.ConstructorParameters;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
public class User {
    private String email;
    private String nickname;
    private LocalDate birthday;

    public User(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
