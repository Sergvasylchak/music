package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.SavedList;
import com.music.lbry.repository.SavedListRepository;
import com.music.lbry.services.SavedListService;
import com.music.lbry.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class SavedListServiceImpl implements SavedListService {
    private final SavedListRepository savedListRepository;
    private final UserService userService;

    @Override
    public Mono<List<SavedList>> findAllLists() {
        return this.userService.getMe()
                .map(user -> this.savedListRepository.findAllByUserId(user.getId()));
    }

    @Override
    public Mono<SavedList> createList(SavedList savedList) {
        return this.userService.getMe()
                .map(user -> {
                    savedList.setUser(user);
                    return this.savedListRepository.save(savedList);
                });
    }

    @Override
    public Mono<SavedList> updateList(Long id, SavedList savedList) {
        return this.userService.getMe()
                .map(user ->
                        this.savedListRepository.findById(id)
                                .filter(l -> l.getUser().getId().equals(user.getId()))
                                .map(l -> {
                                    l.setSongs(savedList.getSongs());
                                    l.setName(savedList.getName());
                                    return this.savedListRepository.save(l);
                                })
                                .orElse(savedList)
                );
    }

    @Override
    public Mono<?> removeList(Long id) {
        return this.userService.getMe()
                .map(user ->
                        this.savedListRepository.findById(id)
                                .filter(l -> l.getUser().getId().equals(user.getId()))
                                .map(l -> {
                                    this.savedListRepository.delete(l);
                                    return ResponseEntity.ok().build();
                                })
                                .orElse(ResponseEntity.badRequest().build())
                );
    }
}
