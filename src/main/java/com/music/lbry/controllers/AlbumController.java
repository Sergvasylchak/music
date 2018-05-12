package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Song;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.SongService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/albums")
@AllArgsConstructor
public class AlbumController extends BaseController {
    private final AlbumService albumService;
    private final SongService songService;

    @GetMapping
    public Mono<List<Album>> findAllByName(@RequestParam(name = "name", defaultValue = "") String name) {
        return this.albumService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Optional<Album> findById(@PathVariable("id") Long id) {
        return this.albumService.findById(id);
    }

    @GetMapping("/{id}/songs")
    public Mono<List<Song>> findSongs(@PathVariable("id") Long id) {
        return this.songService.findAllByAlbumId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        return this.albumService.update(id, album);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Album> add(@RequestBody Album album) {
        return this.albumService.add(album);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return this.albumService.delete(id);
    }
}
