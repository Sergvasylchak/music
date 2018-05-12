package com.music.lbry.basic.models;

import com.music.lbry.basic.models.enums.ExceptionType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionAdvice {
    private Integer code;
    private String status;
    private String message;

    public static ExceptionAdvice of(ExceptionType type, Throwable ex) {
        return ExceptionAdvice.builder()
                .status(type.getMessage())
                .code(type.getCode())
                .message(ex.getMessage())
                .build();
    }
}
