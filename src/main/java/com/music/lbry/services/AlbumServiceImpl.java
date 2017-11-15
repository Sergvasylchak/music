package com.music.lbry.services;

import com.music.lbry.models.entities.Album;
import com.music.lbry.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }
}
