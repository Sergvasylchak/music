package com.music.lbry.services;

import com.music.lbry.models.entities.Album;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Mono<List<Album>> findAllByAuthor(String author);

    Optional<Album> findById(Long id);

    Mono<List<Album>> findAll();
}
