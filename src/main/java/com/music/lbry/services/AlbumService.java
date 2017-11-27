package com.music.lbry.services;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Performer;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Mono<List<Album>> findAllByAuthor(String author);

    Optional<Album> findById(Long id);

    Mono<List<Album>> findAll();

    Mono<Album> add(Album album);

    Mono<Album> update(Long id, Album album);

    Mono<List<Album>> findAllByName(String name);

    Mono<ResponseEntity<Void>> delete(Long id);

    Mono<List<Album>> findAllByAuthorId(Long id);
}
