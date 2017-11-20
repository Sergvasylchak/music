package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.services.AlbumService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/albums")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public List<Album> findAllByAuthor(@RequestParam(required = false) String author) {
        return Optional.ofNullable(author).map(response -> this.albumService.findAllByAuthor(author))
                .orElseGet(this.albumService::findAll);
    }

    @GetMapping("/{id}")
    public Optional<Album> findById(@PathVariable("id") Long id) {
        return this.albumService.findById(id);
    }
}
