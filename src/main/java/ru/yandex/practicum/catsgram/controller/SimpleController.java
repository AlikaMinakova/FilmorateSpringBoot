package ru.yandex.practicum.catsgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //  обработчик понимает что класс содержит обработчики адресов
//  @RequestMapping(value = "/home/corporation/") - попадаем в этот класс по адресу
public class SimpleController {

    //    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}
