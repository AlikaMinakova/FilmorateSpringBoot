package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;

import java.util.Map;

@RestControllerAdvice("ru.yandex.practicum.catsgram.controller")
//обрабатывает ошибки в понятном пользователю виде
//в параметре передаем пакет, из которого будут перехватываться ошибки
//можно указать конкретный класс ошибки
public class ErrorHandler {

    //обработчик, который отлавливает ошибки типа UserAlreadyExistException и возвращает
    //ответ в формате JSON в понятном человеку виде, чтобы пользователь смог исправить запрос
    //в @ExceptionHandler указали какие ошибки обрабаьывать, а в параметре
    //тип родительской ошибки. Сейчас обрабатываются все ошибки, связанные с пользователем
    @ResponseStatus(HttpStatus.BAD_REQUEST) // при возврате вернёт статус 400, а не 200
    @ExceptionHandler({UserAlreadyExistException.class, InvalidEmailException.class})
    public Map<String, String> handleException(final RuntimeException exception) {
        return Map.of("error", exception.getMessage());
        //return new ResponseStatusException(HttpStatus.BAD_REQUEST, "можно и так ошибку вернуть");
        //чтобы отобразился текст, нужно в application.properties добавить server.error.include-message=always
    }
}
