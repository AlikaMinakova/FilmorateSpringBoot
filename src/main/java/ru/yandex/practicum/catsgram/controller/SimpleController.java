package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //  обработчик понимает что класс содержит обработчики адресов
//  @RequestMapping(value = "/home/corporation/") - попадаем в этот класс по адресу
public class SimpleController {
    //  создаём логгер
    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    //    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @GetMapping("/home")
    public String homePage() {
        //  логируем факт получения запроса
        log.info("Запрос получен");    //  log.warn("Запрос получен")

        //  возвращем объект в виде строки
        return "Котограм";
    }
}
