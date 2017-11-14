package com.music.lbry.controllers;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.services.PerformerService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/performers")
@AllArgsConstructor
public class PerformerController {
    private final PerformerService performerService;

    @GetMapping("/performers")
    public List<Performer> findAll() {
        return this.performerService.findAll();
    }

    @GetMapping("/performer")
    public List<Performer> findByName(@RequestParam(required = false) String name) {
        return this.performerService.findAllByName(name);
    }
}
