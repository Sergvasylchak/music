package com.music.lbry.basic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionType {
    ALREADY_EXISTS(401, "Already exists!");

    @Getter
    private int code;
    @Getter
    private String message;
}
