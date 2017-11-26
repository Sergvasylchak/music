package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Song;
import com.music.lbry.repository.SongRepository;
import com.music.lbry.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public Mono<List<Song>> findAll() {
        return Mono.fromCallable(this.songRepository::findAll);
    }

    @Override
    public Mono<List<Song>> findAllByAlbum(String album) {
        return Mono.fromCallable(() -> this.songRepository.findAllByAlbum(album));
    }

    @Override
    public Song add(Song song) {
        return this.songRepository.save(song);
    }

    @Override
    public List<Song> findAllByAlbumId(Long id) {
        return this.songRepository.findAllByAlbumId(id);
    }

    @Override
    public Song update(Long id, Song song) {
        return songRepository.findById(id)
                .map(c -> {
                    c.setName(song.getName());
                    c.setAlbum(song.getAlbum());
                    c.setPerformers(song.getPerformers());
                    return this.songRepository.save(c);
                })
                .orElse(song);
    }
}
