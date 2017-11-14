package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;

import java.util.List;

public interface PerformerService {
    List<Performer> findAll();

    List<Performer> findAllByName(String name);
}
