package com.music.lbry.controllers;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.models.entities.Song;
import com.music.lbry.services.PerformerService;
import com.music.lbry.services.SongService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/performers")
@AllArgsConstructor
public class PerformerController {
    private final PerformerService performerService;
    private final SongService songService;

    @GetMapping("/all")
    public Mono<List<Performer>> findAll() {
        return this.performerService.findAll();
    }

    @GetMapping("/performers")
    public Mono<List<Performer>> findAllByName(@RequestParam String name) {
        return this.performerService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Optional<Performer> findById(@PathVariable("id") Long id) {
        return this.performerService.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Performer> update(@PathVariable("id") Long id, @RequestBody Performer performer) {
        return this.performerService.updatePerformer(id, performer);
    }

    @PostMapping("/add")
    public Mono<Performer> save(@RequestBody Performer performer) {
        return this.performerService.save(performer);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return this.performerService.deletePerformer(id);
    }
}
