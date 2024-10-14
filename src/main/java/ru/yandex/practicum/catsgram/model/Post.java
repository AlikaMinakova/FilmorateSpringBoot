package ru.yandex.practicum.catsgram.model;

import lombok.*;

import java.time.Instant;

//lombok
@Data
@AllArgsConstructor
public class Post {

    private Integer id;
    private final String author;
    private final Instant createDate = Instant.now(); //    create date
    private String description;
    private String photoUrl;
}
