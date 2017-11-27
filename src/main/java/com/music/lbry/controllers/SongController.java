package com.music.lbry.controllers;

import com.music.lbry.models.entities.Song;
import com.music.lbry.services.SongService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/songs")
@AllArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public Mono<List<Song>> findAllByName(@RequestParam(name = "name", defaultValue = "") String name) {
        return this.songService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Optional<Song> findById(@PathVariable("id") Long id) {
        return this.songService.findById(id);
    }

    @PostMapping("/add")
    public Mono<Song> addSong(@RequestBody Song song) {
        return this.songService.add(song);
    }

    @PutMapping("/{id}")
    public Mono<Song> updateSong(@PathVariable("id") Long id, @RequestBody Song song) {
        return this.songService.update(id, song);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSong(@PathVariable("id") Long id) {
        return this.songService.deleteById(id);
    }

}
