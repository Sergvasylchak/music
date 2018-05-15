package com.music.lbry.controllers;

import com.music.lbry.models.entities.SavedList;
import com.music.lbry.services.SavedListService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/lists")
@AllArgsConstructor
public class SavedListController extends BaseController {
    private final SavedListService savedListService;

    @GetMapping
    public Mono<List<SavedList>> findAllLists() {
        return this.savedListService.findAllLists();
    }

    @GetMapping("/{id}")
    public Mono<SavedList> getList(@PathVariable("id") Long id) {
        return this.savedListService.findById(id);
    }

    @PostMapping
    public Mono<SavedList> createList(@RequestBody SavedList savedList) {
        return this.savedListService.createList(savedList);
    }

    @PutMapping("/{id}")
    public Mono<SavedList> createList(@PathVariable("id") Long id, @RequestBody SavedList savedList) {
        return this.savedListService.updateList(id, savedList);
    }

    @DeleteMapping("/{id}")
    public Mono<?> removeList(@PathVariable("id") Long id) {
        return this.savedListService.removeList(id);
    }
}
