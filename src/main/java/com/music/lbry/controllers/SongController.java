package com.music.lbry.controllers;

import com.music.lbry.models.entities.Song;
import com.music.lbry.services.SongService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/songs")
@AllArgsConstructor
public class SongController extends BaseController {
    private final SongService songService;

    @GetMapping
    public Mono<Page<Song>> findAllByName(@RequestParam(name = Constants.PAGE, required = false, defaultValue = Constants.PAGE_NUMBER) Integer page,
                                          @RequestParam(name = Constants.SIZE, required = false, defaultValue = Constants.PAGE_SIZE) Integer size,
                                          @RequestParam(name = Constants.NAME, defaultValue = Constants.BLANK) String name) {
        return this.songService.findAllByName(name, getPageable(page, size));
    }

    @GetMapping("/{id}")
    public Optional<Song> findById(@PathVariable("id") Long id) {
        return this.songService.findById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Song> addSong(@RequestBody Song song) {
        return this.songService.add(song);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Song> updateSong(@PathVariable("id") Long id, @RequestBody Song song) {
        return this.songService.update(id, song);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Void>> deleteSong(@PathVariable("id") Long id) {
        return this.songService.deleteById(id);
    }
}
