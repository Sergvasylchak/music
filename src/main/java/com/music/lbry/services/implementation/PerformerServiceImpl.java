package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.Performer;
import com.music.lbry.repository.PerformerRepository;
import com.music.lbry.services.PerformerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;

    @Override
    public Mono<List<Performer>> findAll() {
        return Mono.fromCallable(this.performerRepository::findAll);
    }

    @Override
    public Mono<List<Performer>> findAllByName(String name) {
        return Mono.fromCallable(() -> this.performerRepository.findAllByName(name));
    }

    @Override
    public Optional<Performer> findById(Long id) {
        return this.performerRepository.findById(id);
    }

    @Override
    public Mono<Performer> findByName(String name) {
        return Mono.justOrEmpty(this.performerRepository.findByName(name));
    }

    @Override
    public Mono<Performer> updatePerformer(Long id, Performer performer) {
        return this.performerRepository.findById(id)
                .map(c -> {
                    c.setName(performer.getName());
                    return Mono.fromCallable(() -> this.performerRepository.save(c))
                            .onErrorReturn(c);
                }).orElse(Mono.empty());
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePerformer(Long id) {
        return Mono.fromCallable(() -> {
            this.performerRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<List<Performer>> saveAll(List<Performer> performers) {
        return Mono.fromCallable(() -> this.performerRepository.saveAll(performers))
                .onErrorReturn(Collections.emptyList());
    }

    @Override
    public Mono<Performer> save(Performer performer) {
        return Mono.fromCallable(() -> this.performerRepository.save(performer))
                .doOnError(c -> Mono.empty());
    }
}
