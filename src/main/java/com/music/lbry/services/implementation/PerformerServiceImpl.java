package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.repository.PerformerRepository;
import com.music.lbry.services.PerformerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Optional<Performer> findById(Long id) {
        return this.performerRepository.findById(id);
    }

    @Override
    public Optional<Performer> updatePerformer(Long id, Performer performer) {
        return this.performerRepository.findById(id)
                .map(c -> {
                    c.setName(performer.getName());
                    return this.performerRepository.save(c);
                });
    }

    @Override
    public Mono<ResponseEntity<Object>> deletePerformer(Long id) {
        return Mono.fromCallable(() -> {
            this.performerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).onErrorReturn(ResponseEntity.notFound().build());
    }

    @Override
    public List<Performer> saveAll(List<Performer> performers) {
        return this.performerRepository.saveAll(performers);
    }

    @Override
    public Optional<Performer> save(Performer performer) {
        return Optional.of(this.performerRepository.save(performer));
    }
}
