package com.music.lbry.repository;

import com.music.lbry.models.entities.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
    @Query("SELECT r FROM com.music.lbry.models.entities.Performer r " +
            " WHERE r.name LIKE %:name%")
    List<Performer> findAllByName(@Param("name") String name);

    Optional<Performer> findById(Long id);

    @Query("SELECT r FROM Performer r WHERE r.name = :name")
    Performer findByName(@Param("name") String name);
}
