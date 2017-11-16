package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.services.AlbumService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/albums")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/album")
    public List<Album> findAllByAuthor(@PathVariable("query") String query) {
        return this.albumService.findAllByAuthorsByQuery(query);
    }
}
