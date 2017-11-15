package com.music.lbry.controllers;

import com.music.lbry.models.entities.Album;
import com.music.lbry.services.AlbumService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/albums")
@AllArgsConstructor
public class AlbumController {
    private AlbumService albumService;

    @GetMapping("albums")
    public List<Album> findAll(){
        return this.albumService.findAll();
    }
}
