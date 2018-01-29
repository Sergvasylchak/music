package com.music.lbry.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class BaseController {
    protected Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(page - 1, size);
    }
}
