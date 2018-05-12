package com.music.lbry.basic.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionType {
    ALREADY_EXISTS(409, "Already exists"),
    INTERNAL_SERVER_ERROR(500, "Unexpected exception"),
    UNAUTHORIZED(401, "Unauthorized");

    @Getter
    private int code;
    @Getter
    private String message;
}
