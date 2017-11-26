package com.music.lbry.services;

import com.music.lbry.models.entities.Song;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SongService {
    Mono<List<Song>> findAll();

    Mono<List<Song>> findAllByAlbum(String album);

    Song add(Song song);

    List<Song> findAllByAlbumId(Long id);

    Song update(Long id, Song song);
}
