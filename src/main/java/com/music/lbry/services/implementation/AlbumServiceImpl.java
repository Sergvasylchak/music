package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Album;
import com.music.lbry.repository.AlbumRepository;
import com.music.lbry.services.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Mono<List<Album>> findAllByAuthor(String author) {
        return Mono.fromCallable(() -> this.albumRepository.findAllByAuthorsByQuery(author));
    }

    @Override
    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }

    @Override
    public Mono<List<Album>> findAll() {
        return Mono.fromCallable(this.albumRepository::findAll);
    }
}
