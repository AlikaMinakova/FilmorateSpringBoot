package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Follow {
    private String author;
    private String follower;
}
