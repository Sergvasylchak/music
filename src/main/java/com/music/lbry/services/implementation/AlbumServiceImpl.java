package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Performer;
import com.music.lbry.models.entities.Song;
import com.music.lbry.repository.AlbumRepository;
import com.music.lbry.repository.SongRepository;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Mono<List<Album>> findAllByAuthor(String author) {
        return Mono.fromCallable(() -> this.albumRepository.findAllByName(author));
    }

    @Override
    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }

    @Override
    public Mono<List<Album>> findAll() {
        return Mono.fromCallable(this.albumRepository::findAll);
    }

    @Override
    public Mono<Album> add(Album album) {
        return Mono.fromCallable(() -> this.albumRepository.save(album)).onErrorResume(c -> Mono.empty());
    }

    @Override
    public Mono<Album> update(Long id, Album album) {
        return this.albumRepository.findById(id)
                .map(c -> {
                    c.setName(album.getName());
                    c.setAuthor(album.getAuthor());
                    return Mono.fromCallable(() -> this.albumRepository.save(c))
                            .onErrorResume(f -> Mono.empty());
                }).orElse(Mono.empty());
    }

    @Override
    public Mono<List<Album>> findAllByName(String name) {
        return Mono.fromCallable(() -> this.albumRepository.findAllByName(name));
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(Long id) {
        return Mono.fromCallable(() -> {
            this.albumRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).onErrorReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    public Mono<List<Album>> findAllByAuthorId(Long id) {
        return Mono.fromCallable(() -> this.albumRepository.findAllByAuthorId(id));
    }
}
