package ru.yandex.practicum.catsgram.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Post {
    private final String author;
    private final Instant createDate = Instant.now(); //    create date
    private String description;
    private String photoUrl;
}
