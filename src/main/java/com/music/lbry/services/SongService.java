package com.music.lbry.services;

import com.music.lbry.models.entities.Song;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SongService {
    Mono<List<Song>> findAll();

    Mono<List<Song>> findAllByAlbum(String album);
}
