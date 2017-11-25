package com.music.lbry.controllers;

import com.music.lbry.models.entities.Song;
import com.music.lbry.services.SongService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ENDPOINT)
@AllArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("/songs/all")
    public Mono<List<Song>> findAll() {
        return this.songService.findAll();
    }

}
