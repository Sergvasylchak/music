package com.music.lbry.services;

import com.music.lbry.models.entities.Song;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Mono<List<Song>> findAll();

    Mono<List<Song>> findAllByAlbumId(Long id);

    Mono<List<Song>> findAllByAuthorId(Long id);

    Mono<List<Song>> findAllByName(String name);

    Mono<Song> add(Song song);

    Mono<Song> update(Long id, Song song);

    Mono<ResponseEntity<Void>> deleteById(Long id);

    Optional<Song> findById(Long id);
}
