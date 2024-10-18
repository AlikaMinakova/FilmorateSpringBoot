package ru.yandex.practicum.catsgram.model;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

//lombok
@Data
@AllArgsConstructor
public class Post {

    private Integer id;
    private final User author;
    private final LocalDate createDate; //    create date
    private String description;
    private String photoUrl;

    public Post(User author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        this.createDate = LocalDate.now();
    }
}
