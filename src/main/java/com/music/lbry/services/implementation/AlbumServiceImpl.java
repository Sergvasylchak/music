package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Song;
import com.music.lbry.repository.AlbumRepository;
import com.music.lbry.repository.SongRepository;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final SongService songService;

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

    @Override
    public Album add(Album album) {
        return this.albumRepository.save(album);
    }

    @Override
    public List<Album> findAllByName(String name) {
        return this.albumRepository.findAllByName(name);
    }

    @Override
    public void delete(Long id) {
        this.songService.findAllByAlbumId(id).forEach(c -> {
            c.setAlbum(null);
            this.songService.update(c.getId(), c);
        });
        this.albumRepository.deleteById(id);
    }
}
