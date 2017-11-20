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

    @GetMapping
    public List<Performer> findByName(@RequestParam(required = false) String name) {
        return Optional.ofNullable(name).map(response -> this.performerService.findAllByName(name))
                .orElseGet(this.performerService::findAll);
    }

    @GetMapping("/{id}")
    public Optional<Performer> findById(@PathVariable("id") Long id) {
        return this.performerService.findById(id);
    }

    @PutMapping("/{id}")
    public Optional<Performer> updatePerformer(@PathVariable("id") Long id, @RequestBody Performer performer) {
        return this.performerService.updatePerformer(id, performer);
    }
}
