package ru.yandex.practicum.catsgram.model;

import lombok.*;

import javax.management.ConstructorParameters;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
//@Data - заменяет 5 базовых аннотаций (@RequiredArgsConstructor - все final поля)
public class User {
    private String email;
    @EqualsAndHashCode.Exclude
    private String nickname;
    @EqualsAndHashCode.Exclude
    private LocalDate birthday;
}
