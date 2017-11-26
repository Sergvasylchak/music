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
    private final PerformerService performerService;

    @GetMapping
    public Mono<List<Album>> findAllByAuthor(@RequestParam String author) {
        return this.albumService.findAllByAuthor(author);
    }

    @GetMapping("/{id}")
    public Optional<Album> findById(@PathVariable("id") Long id) {
        return this.albumService.findById(id);
    }

    @GetMapping("/all")
    public Mono<List<Album>> findAll() {
        return this.albumService.findAll();
    }

    @PostMapping("/add")
    public Album add(@RequestBody Album album) {
        return this.albumService.add(album);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.albumService.delete(id);
    }
}
