package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;

import java.util.List;
import java.util.Optional;

public interface PerformerService {
    List<Performer> findAll();

    List<Performer> findAllByName(String name);

    Optional<Performer> findPerformerById(Long id);

    Optional<Performer> updatePerformer(Long id, Performer performer);
}
