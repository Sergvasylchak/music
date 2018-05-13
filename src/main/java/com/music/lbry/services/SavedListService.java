package com.music.lbry.services;

import com.music.lbry.models.entities.SavedList;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SavedListService {
    Mono<List<SavedList>> findAllLists();

    Mono<SavedList> createList(SavedList savedList);

    Mono<SavedList> updateList(Long id, SavedList savedList);

    Mono<?> removeList(Long id);
}
