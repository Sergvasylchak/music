package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Performer;
import com.music.lbry.models.entities.Song;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.PerformerService;
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
@RequestMapping(Constants.API_ENDPOINT + "/performers")
@AllArgsConstructor
public class PerformerController extends BaseController {
    private final PerformerService performerService;
    private final SongService songService;
    private final AlbumService albumService;

    @GetMapping
    public Mono<List<Performer>> findAllByName(@RequestParam(name = "name", defaultValue = "") String name) {
        return this.performerService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Optional<Performer> findById(@PathVariable("id") Long id) {
        return this.performerService.findById(id);
    }

    @GetMapping("/{id}/albums")
    public Mono<List<Album>> findAlbums(@PathVariable("id") Long id) {
        return this.albumService.findAllByAuthorId(id);
    }

    @GetMapping("/{id}/songs")
    public Mono<List<Song>> findSongs(@PathVariable("id") Long id) {
        return this.songService.findAllByAuthorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Performer> update(@PathVariable("id") Long id, @RequestBody Performer performer) {
        return this.performerService.updatePerformer(id, performer);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Performer> save(@RequestBody Performer performer) {
        return this.performerService.save(performer);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return this.performerService.deletePerformer(id);
    }
}
