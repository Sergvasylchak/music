package com.music.lbry.basic.security;

import org.springframework.security.access.AccessDeniedException;

public class UnauthorizedException extends AccessDeniedException {

    public UnauthorizedException() {
        super("Access Denied");
    }
}
