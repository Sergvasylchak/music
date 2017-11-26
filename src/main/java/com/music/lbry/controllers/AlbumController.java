package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Performer;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.PerformerService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/albums")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/albums")
    public Mono<List<Album>> findAllByAuthor(@RequestParam String author) {
        return this.albumService.findAllByAuthor(author);
    }

    @GetMapping("/{name}")
    public Mono<List<Album>> findAllByName(@PathVariable("name") String name) {
        return this.albumService.findAllByName(name);
    }

    @GetMapping("/album/{id}")
    public Optional<Album> findById(@PathVariable("id") Long id) {
        return this.albumService.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        return this.albumService.update(id, album);
    }

    @GetMapping("/all")
    public Mono<List<Album>> findAll() {
        return this.albumService.findAll();
    }

    @PostMapping("/add")
    public Mono<Album> add(@RequestBody Album album) {
        return this.albumService.add(album);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return this.albumService.delete(id);
    }
}
