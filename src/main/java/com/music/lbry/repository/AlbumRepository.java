package com.music.lbry.repository;

import com.music.lbry.models.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT a FROM Album a WHERE a.author.name LIKE %:author%")
    List<Album> findAllByAuthor(@Param("author") String author);

    Optional<Album> findById(Long id);

    @Query("SELECT a FROM Album a WHERE a.name LIKE %:name%")
    List<Album> findAllByName(@Param("name") String name);
}
