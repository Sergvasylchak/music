package com.music.lbry.repository;

import com.music.lbry.models.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE s.album.name LIKE %:album%")
    List<Song> findAllByAlbum(@Param("album") String album);

    @Query("SELECT s FROM Song s WHERE s.album.id = :id")
    List<Song> findAllByAlbumId(@Param("id") Long id);
}
