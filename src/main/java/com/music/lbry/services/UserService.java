package com.music.lbry.services;

import com.music.lbry.models.entities.LibraryUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserService {
    Mono<Page<LibraryUser>> findAllBySearch(String search, Pageable pageable);

    Mono<Optional<LibraryUser>> findById(Long id);

    Mono<LibraryUser> add(LibraryUser libraryUser);

    Mono<Optional<LibraryUser>> findByUsername(String usename);
}
