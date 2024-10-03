package ru.yandex.practicum.catsgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//класс для создания эндпоинтов(обработчики адреса)
@RestController //обработчик пониает что класс содержит обработчики адресов
public class SimpleController {

    @RequestMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}
