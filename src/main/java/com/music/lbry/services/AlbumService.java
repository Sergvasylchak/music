package com.music.lbry.services;

import com.music.lbry.models.entities.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();

    List<Album> findAllByAuthor(String author);

    Optional<Album> findById(Long id);

}
