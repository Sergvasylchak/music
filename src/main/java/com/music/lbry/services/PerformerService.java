package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface PerformerService {
    Mono<List<Performer>> findAll();

    Mono<List<Performer>> findAllByName(String name);

    Optional<Performer> findById(Long id);

    Mono<Performer> findByName(String name);

    Mono<Performer> updatePerformer(Long id, Performer performer);

    Mono<ResponseEntity<Void>> deletePerformer(Long id);

    Mono<List<Performer>> saveAll(List<Performer> performers);

    Mono<Performer> save(Performer performer);
}
