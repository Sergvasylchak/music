package com.music.lbry.services;

import com.music.lbry.models.entities.Album;
import com.music.lbry.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }

    @Override
    public List<Album> findAllByAuthor(String author) {
        return this.albumRepository.findAllByAuthorsByQuery(author);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }
}
