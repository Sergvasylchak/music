package com.music.lbry.repository;

import com.music.lbry.models.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT a FROM Album a WHERE a.author = :author")
    List<Album> findAllByAuthorName(String author);
}
