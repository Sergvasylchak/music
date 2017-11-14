package com.music.lbry.controllers;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.services.PerformerService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Performer> findPerformerById(@PathVariable("id") Long id) {
        return this.performerService.findPerformerById(id);
    }
}
