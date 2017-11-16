package com.music.lbry.repository;

import com.music.lbry.models.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT a FROM Album a WHERE a.author LIKE %:query%")
    List<Album> findAllByAuthorsByQuery(String author);

    Optional<Album> findAlbumById(Long id);
}
