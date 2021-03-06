package com.music.lbry.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    //Param constants
    public static final String PAGE_NUMBER = "1";
    public static final String PAGE = "page";
    public static final String NAME = "name";
    public static final String SIZE = "size";
    public static final String PAGE_SIZE = "10";

    public static final String API_ENDPOINT = "/api/lbry";
    public static final String AUTH_ENDPOINT = "/auth/lbry";
    public static final String BLANK = "";
    public static final String BEARER = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String ROLE_KEY = "Role";
}
