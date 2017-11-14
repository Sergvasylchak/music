package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.repository.PerformerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Performer> findPerformerById(Long id) {
        return this.performerRepository.findPerformerById(id);
    }

    @Override
    public Optional<Performer> updatePerformer(Long id, Performer performer) {
        return this.performerRepository.findPerformerById(id)
                .map(c -> {
                    c.setName(performer.getName());
                    return this.performerRepository.save(c);
                });
    }
}
