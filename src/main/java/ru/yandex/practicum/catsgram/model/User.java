package ru.yandex.practicum.catsgram.model;

import lombok.*;

import java.time.LocalDate;

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
