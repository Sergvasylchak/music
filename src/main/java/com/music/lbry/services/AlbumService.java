package com.music.lbry.services;

import com.music.lbry.models.entities.Album;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();
}
