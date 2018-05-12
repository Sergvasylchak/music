package com.music.lbry.basic.controllers;

import com.music.lbry.basic.models.enums.ExceptionType;
import com.music.lbry.basic.models.ExceptionAdvice;
import com.music.lbry.basic.security.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionAdvice handleUnauthorizedException() {
        return ExceptionAdvice.of(ExceptionType.UNAUTHORIZED, new UnauthorizedException());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLException.class)
    public ExceptionAdvice handleSqlException(SQLException ex) throws IOException {
        LOGGER.error(ex.getMessage());
        return ExceptionAdvice.of(ExceptionType.ALREADY_EXISTS, ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionAdvice handleInternalServerError(Exception ex) {
        LOGGER.error(ex.getMessage());
        return ExceptionAdvice.of(ExceptionType.INTERNAL_SERVER_ERROR, ex);
    }
}
