package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.repository.PerformerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;

    @Override
    public List<Performer> findAll() {
        return this.performerRepository.findAll();
    }

    @Override
    public List<Performer> findAllByName(String name) {
        return this.performerRepository.findAllByName(name);
    }
}
