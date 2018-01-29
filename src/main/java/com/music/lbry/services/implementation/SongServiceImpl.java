package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Song;
import com.music.lbry.repository.SongRepository;
import com.music.lbry.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public Mono<List<Song>> findAll() {
        return Mono.fromCallable(this.songRepository::findAll);
    }

    @Override
    public Mono<List<Song>> findAllByAlbumId(Long id) {
        return Mono.fromCallable(() -> this.songRepository.findAllByAlbumId(id));
    }

    @Override
    public Mono<List<Song>> findAllByAuthorId(Long id) {
        return Mono.fromCallable(() -> this.songRepository.findAllByPerformerId(id));
    }

    @Override
    public Mono<Page<Song>> findAllByName(String name, Pageable pageable) {
        return Mono.fromCallable(() -> this.songRepository.findAllByName(name, pageable));
    }

    @Override
    public Mono<Song> add(Song song) {
        return Mono.fromCallable(() -> this.songRepository.save(song)).onErrorResume(c -> Mono.empty());
    }

    @Override
    public Mono<Song> update(Long id, Song song) {
        return this.songRepository.findById(id)
                .map(c -> {
                    c.setName(song.getName());
                    c.setAlbum(song.getAlbum());
                    c.setPerformers(song.getPerformers());
                    return Mono.fromCallable(() -> this.songRepository.save(c))
                            .onErrorResume(f -> Mono.empty());
                }).orElse(Mono.empty());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteById(Long id) {
        return Mono.fromCallable(() -> {
            this.songRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).onErrorReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }
}
