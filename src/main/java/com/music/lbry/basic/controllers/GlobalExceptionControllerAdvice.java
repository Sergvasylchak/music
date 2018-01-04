package com.music.lbry.basic.controllers;

import com.music.lbry.basic.enums.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(SQLException.class)
    public Integer handleSqlException(SQLException ex, HttpRequest request) {
        LOGGER.error(ex.getMessage());
        return ExceptionType.ALREADY_EXISTS.getCode();
    }
}
