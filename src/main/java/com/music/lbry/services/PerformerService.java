package com.music.lbry.services;

import com.music.lbry.models.entities.Performer;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface PerformerService {
    List<Performer> findAll();

    List<Performer> findAllByName(String name);

    Optional<Performer> findById(Long id);

    Optional<Performer> updatePerformer(Long id, Performer performer);

    Mono<ResponseEntity<Object>> deletePerformer(Long id);

    List<Performer> saveAll(List<Performer> performers);

    Optional<Performer> save(Performer performer);
}
